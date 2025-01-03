package com.online.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TCPChannelHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        String trimMsg = msg.trim(); 
        System.out.println(ctx.channel().remoteAddress() + " " + trimMsg);

        if ("Hello".equalsIgnoreCase(trimMsg)) { 
            ctx.channel().writeAndFlush("Wassup boy..\n");
        } else if ("Dc".equalsIgnoreCase(trimMsg)) { 
            ctx.channel().writeAndFlush("Goodbye!\n").addListener(future -> {
                if (future.isSuccess()) {
                    ctx.channel().close(); 
                }
            });
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " Channel active");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " Channel Inactive");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Log the exception
        System.err.println("Exception caught: " + cause.getMessage());
        cause.printStackTrace();

        // Close the connection on exception
        ctx.close();
    }
}
