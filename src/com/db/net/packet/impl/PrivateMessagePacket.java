/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.net.packet.impl;

import com.db.net.ServerHandler;
import com.db.net.packet.PacketBuilder;
import com.db.net.packet.PacketHandler;
import com.db.player.Player;
import com.db.util.Constants;
import java.util.ArrayList;
import org.jboss.netty.channel.ChannelHandlerContext;

/**
 *
 * @author Amjad
 */
public class PrivateMessagePacket implements PacketHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, ArrayList<Object> packet) {
        final String name = packet.get(0).toString();
        final String from = packet.get(1).toString();
        final String message = packet.get(2).toString();
        final PacketBuilder pb = new PacketBuilder(Constants.PRIVATE_MESSAGE_PACKET);
        pb.add(from);
        pb.add(message.replace("/pm " + name + " ", ""));
        final Player player = ServerHandler.connectedClients.get(name);
        if (player != null) {
            player.write(pb.getPacket());
        }
    }
}
