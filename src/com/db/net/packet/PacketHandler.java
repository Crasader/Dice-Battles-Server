package com.db.net.packet;

import java.util.ArrayList;
import org.jboss.netty.channel.ChannelHandlerContext;

public interface PacketHandler {

    public void handle(ChannelHandlerContext ctx, ArrayList<Object> packet);
}