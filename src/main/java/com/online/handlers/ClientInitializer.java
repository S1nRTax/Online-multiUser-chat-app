package com.online.handlers;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ClientInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		
		ch.pipeline().addLast(new StringDecoder()); // Decodes incoming messages
		ch.pipeline().addLast(new StringEncoder()); // Encodes outgoing messages
		ch.pipeline().addLast(new TCPClientHandler());
	}

}
