package com.ltb.gateway.socket.handlers;

import com.ltb.gateway.mapping.HttpStatement;
import com.ltb.gateway.session.Configuration;
import com.ltb.gateway.socket.BaseHandler;
import com.ltb.gateway.socket.aggrement.AgreementConstants;
import com.ltb.gateway.socket.aggrement.GatewayResultMessage;
import com.ltb.gateway.socket.aggrement.RequestParser;
import com.ltb.gateway.socket.aggrement.ResponseParser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GatewayServerHandler extends BaseHandler<FullHttpRequest> {

    private final Logger logger = LoggerFactory.getLogger(GatewayServerHandler.class);

    private final Configuration configuration;

    public GatewayServerHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {
        logger.info("网关接收请求 uri：{} method：{}", request.uri(), request.method());
        try {
            // 解析请求参数
            RequestParser requestParser = new RequestParser(request);
            String uri = requestParser.getUri();

            //保存信息,HttpStatement,token
            HttpStatement httpStatement = configuration.getHttpStatement(uri);
            channel.attr(AgreementConstants.HTTP_STATEMENT).set(httpStatement);

            //放行服务
            request.retain();
            ctx.fireChannelRead(request);
        } catch (Exception e) {
            // 4. 封装返回结果
            DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._500.getCode(), "网关协议调用失败！" + e.getMessage()));
            channel.writeAndFlush(response);

        }


    }
}





