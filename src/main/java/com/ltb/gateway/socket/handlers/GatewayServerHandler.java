package com.ltb.gateway.socket.handlers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ltb.gateway.bind.IGenericReference;
import com.ltb.gateway.socket.BaseHandler;
import com.ltb.gateway.session.GatewaySession;
import com.ltb.gateway.session.defaults.DefaultGatewaySessionFactory;
import com.ltb.gateway.socket.aggrement.RequestParser;
import com.ltb.gateway.socket.aggrement.ResponseParser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class GatewayServerHandler extends BaseHandler<FullHttpRequest> {

    private final Logger logger = LoggerFactory.getLogger(GatewayServerHandler.class);

    private final DefaultGatewaySessionFactory gatewaySessionFactory;

    public GatewayServerHandler(DefaultGatewaySessionFactory gatewaySessionFactory) {
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    @Override
    protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {
        logger.info("网关接收请求 uri：{} method：{}", request.uri(), request.method());
        // 解析请求参数
        RequestParser requestParser = new RequestParser(request);
        String uri = requestParser.getUri();
        if(null == uri) return;
        Map<String, Object> args = requestParser.parse();

        //调用会话服务
        GatewaySession gatewaySession = gatewaySessionFactory.openSession(uri);
        IGenericReference reference = gatewaySession.getMapper();
        Object res = reference.$invoke(args);

        //封装返回结果
        DefaultFullHttpResponse response = new ResponseParser().parse(res);
        channel.writeAndFlush(response);


    }
}





