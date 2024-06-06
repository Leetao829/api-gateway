package com.ltb.gateway.core.socket;

import com.ltb.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import com.ltb.gateway.core.socket.handlers.AuthorizationHandler;
import com.ltb.gateway.core.socket.handlers.ProtocolDataHandler;
import com.ltb.gateway.core.session.Configuration;
import com.ltb.gateway.core.socket.handlers.GatewayServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * 会话管道初始化类
 *
 * @author leetao
 */
public class GatewayChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final DefaultGatewaySessionFactory gatewaySessionFactory;
    private final Configuration configuration;

    public GatewayChannelInitializer(Configuration configuration, DefaultGatewaySessionFactory gatewaySessionFactory) {
        this.configuration = configuration;
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline line = channel.pipeline();
        line.addLast(new HttpRequestDecoder());
        line.addLast(new HttpResponseEncoder());
        line.addLast(new HttpObjectAggregator(1024 * 1024));
        line.addLast(new GatewayServerHandler(configuration));
        line.addLast(new AuthorizationHandler(configuration));
        line.addLast(new ProtocolDataHandler(gatewaySessionFactory));
    }

}
