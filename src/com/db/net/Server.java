/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.net;

import com.db.util.Constants;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

/**
 *
 * @author Amjad
 */
public class Server {

    public static void bind() {
        final ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool()));

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(
                        new ObjectEncoder(),
                        new ObjectDecoder(),
                        new ServerHandler());
            }
        });

        bootstrap.bind(new InetSocketAddress(Constants.PORT));
    }
}