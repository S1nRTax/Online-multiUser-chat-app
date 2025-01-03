package com.online.commands;

import io.netty.channel.nio.NioEventLoopGroup;

public class ServerCommands {
	
	public static void shutdownServer(NioEventLoopGroup bossGroup, NioEventLoopGroup workerGroup) {
        System.out.println("Shutting down the server...");
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
        System.out.println("Server shutdown complete.");
    }
	
}
