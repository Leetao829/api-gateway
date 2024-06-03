package com.ltb.gateway.socket.handlers;

import com.ltb.gateway.bind.IGenericReference;
import com.ltb.gateway.executor.result.SessionResult;
import com.ltb.gateway.session.GatewaySession;
import com.ltb.gateway.session.defaults.DefaultGatewaySessionFactory;
import com.ltb.gateway.socket.BaseHandler;
import com.ltb.gateway.socket.aggrement.AgreementConstants;
import com.ltb.gateway.socket.aggrement.GatewayResultMessage;
import com.ltb.gateway.socket.aggrement.RequestParser;
import com.ltb.gateway.socket.aggrement.ResponseParser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 协议数据处理
 *
 * @author leetao
 */
public class ProtocolDataHandler extends BaseHandler<FullHttpRequest> {

    private final Logger logger = LoggerFactory.getLogger(ProtocolDataHandler.class);

    private final DefaultGatewaySessionFactory gatewaySessionFactory;

    public ProtocolDataHandler(DefaultGatewaySessionFactory gatewaySessionFactory){
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    @Override
    protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {
        logger.info("网关接收请求【消息】 uri：{} method：{}", request.uri(), request.method());
        try {
            //解析请求参数
            RequestParser requestParser = new RequestParser(request);
            String uri = requestParser.getUri();
            if(null == uri) return;
            Map<String, Object> args = requestParser.parse();

            //调用会话服务
            GatewaySession gatewaySession = gatewaySessionFactory.openSession(uri);
            IGenericReference reference = gatewaySession.getMapper();
            SessionResult result = reference.$invoke(args);

            //封装返回结果
            DefaultFullHttpResponse response = new ResponseParser().parse("0000".equals(result.getCode()) ? GatewayResultMessage.buildSuccess(result.getData()) : GatewayResultMessage.buildError(AgreementConstants.ResponseCode._404.getCode(), "网关协议调用失败！"));
            channel.writeAndFlush(response);
        } catch (Exception e) {
            // 4. 封装返回结果
            DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._502.getCode(), "网关协议调用失败！" + e.getMessage()));
            channel.writeAndFlush(response);

        }

    }
}
