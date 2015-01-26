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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jboss.netty.channel.ChannelHandlerContext;

/**
 *
 * @author Amjad
 */
public class JoinTablePacket implements PacketHandler {

    private static final HashMap<String, Player> tableManager = new HashMap<String, Player>();

    @Override
    public void handle(ChannelHandlerContext ctx, ArrayList<Object> packet) {
        final String name = packet.get(0).toString();
        final String username = packet.get(1).toString();
        final boolean created = Boolean.parseBoolean(packet.get(2).toString());
        for (int i = 0; i < CreateTablePacket.tables.size(); i++) {
            if (CreateTablePacket.tables.get(i)[0].equals(name)) {
                String[] playerSplit = CreateTablePacket.tables.get(i)[2].toString().split("/");
                int players = Integer.parseInt(playerSplit[0]) + 1;
                int maxPlayers = Integer.parseInt(playerSplit[1]);
                if (!created) {
                    if (players >= maxPlayers) {
                        CreateTablePacket.tables.remove(i);
                    } else {
                        CreateTablePacket.tables.set(i, new Object[]{CreateTablePacket.tables.get(i)[0], CreateTablePacket.tables.get(i)[1], players + "/" + maxPlayers});
                    }
                }
                final Player player = (Player) ServerHandler.connectedClients.get(username);
                //TODO: Send everything else :)
                final PacketBuilder pb1 = new PacketBuilder(Constants.NEW_USER_JOINED_PACKET);
                pb1.add(player.username);
                final PacketBuilder pb2 = new PacketBuilder(Constants.JOIN_TABLE_PACKET);
                pb2.add(1);
                for (Iterator iter = tableManager.entrySet().iterator(); iter.hasNext();) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    if (entry.getKey().equals(name)) {
                        Player playerSend = (Player) entry.getValue();
                        pb2.add(playerSend.username);
                        playerSend.write(pb1.getPacket());
                    }
                }
                tableManager.put(name, player);
                ctx.getChannel().write(pb2.getPacket());
                return;
            }
        }
    }
}
