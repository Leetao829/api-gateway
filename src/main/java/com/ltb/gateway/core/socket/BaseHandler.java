package com.ltb.gateway.core.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 数据处理器基类
 *
 * @author leetao
 */
public abstract class BaseHandler<T> extends SimpleChannelInboundHandler<T> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {
        session(ctx,ctx.channel(),msg);
    }

    protected abstract void session(ChannelHandlerContext ctx, Channel channel, T request);
}
