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
import java.util.Iterator;
import java.util.Map;
import org.jboss.netty.channel.ChannelHandlerContext;

/**
 *
 * @author Amjad
 */
public class PublicMessagePacket implements PacketHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, ArrayList<Object> packet) {
        final String from = packet.get(0).toString();
        final String message = packet.get(1).toString();
        final PacketBuilder pb = new PacketBuilder(Constants.PUBLIC_MESSAGE_PACKET);
        pb.add(from);
        pb.add(message);

        for (Iterator iter = ServerHandler.connectedClients.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry) iter.next();
            Player player = (Player) entry.getValue();
            if (from.equals(player.username)) {
                continue;
            }
            player.write(pb.getPacket());
        }
    }
}
