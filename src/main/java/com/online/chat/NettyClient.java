package com.online.chat;


import java.util.Scanner;

import com.online.handlers.ClientInitializer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
	
	
	public static void main(String[] args)throws InterruptedException {
		
		String host = "localhost";
		int port = 6969;
		
		EventLoopGroup group = new NioEventLoopGroup(); 
		
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group)
				.channel(NioSocketChannel.class)
				.handler(new ClientInitializer());
			
			
				// Connect to the server.
				ChannelFuture future = bootstrap.connect(host, port).sync();
				System.out.println("Connected to server at "+ host + ":" + port);
				
				//get the channel
				Channel channel = future.channel();
				
				// Allow user to send messages
				Scanner scanner = new Scanner(System.in);
				while (true){
					System.out.print("Enter message(use dc to disconnect): ");
					String message = scanner.nextLine();
					if("dc".equalsIgnoreCase(message)) {
						channel.close();
						break;
					}
					channel.writeAndFlush(message + "\n");
				}
				
				channel.closeFuture().sync();
		}finally {
			group.shutdownGracefully();
		}
	}
	
	
}
