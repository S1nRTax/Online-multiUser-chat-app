package com.online.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TCPClientHandler extends SimpleChannelInboundHandler<String>{
		
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
			// prints received msgs from the server.
			System.out.println("Server: "+ msg);
		}
		
	 	@Override
	    public void channelActive(ChannelHandlerContext ctx) throws Exception {
	        System.out.println("Connection established with server!");
	    }

	    @Override
	    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	        System.out.println("Disconnected from server.");
	    }

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	        System.err.println("Error: " + cause.getMessage());
	        cause.printStackTrace();
	        ctx.close(); // Close connection on error
	    }

		
}
