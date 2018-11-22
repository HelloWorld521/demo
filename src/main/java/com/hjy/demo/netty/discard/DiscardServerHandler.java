package com.hjy.demo.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * DISCARD 丢弃任何接受数据协议
 *  Handles a server-side channel
 *
 *  ChannelInboundHandlerAdapter 提供各种事件的处理方法
 * @author hjy
 * @date 2018/11/19
 **/
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 从客户端收到消息时, 调用此方法
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Discard the received data silently
        // 释放引用计数对象
        ((ByteBuf) msg).release();
    }

    /**
     * 本事件异常处理方法, 记录捕获异常,并关闭关联通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // Close the connection when an exception is raised
        cause.printStackTrace();
        ctx.close();
    }
}
