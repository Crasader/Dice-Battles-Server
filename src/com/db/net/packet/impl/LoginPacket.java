/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.net.packet.impl;

import com.db.net.ServerHandler;
import com.db.net.packet.PacketBuilder;
import com.db.net.packet.PacketHandler;
import com.db.net.packet.PacketManager;
import com.db.player.Player;
import com.db.player.PlayerHandler;
import com.db.player.PlayerManager;
import com.db.util.Constants;
import java.util.ArrayList;
import org.jboss.netty.channel.ChannelHandlerContext;

/**
 *
 * @author Amjad
 */
public class LoginPacket implements PacketHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, ArrayList<Object> packet) {
        final String username = packet.get(0).toString();
        final String password = packet.get(1).toString();
        final int returnCode = PlayerManager.load(username, password);
        final PacketBuilder pb = new PacketBuilder(Constants.LOGIN_PACKET);
        pb.add(returnCode);
        ctx.getChannel().write(pb.getPacket());
        if (returnCode == 2) {
            final Player player = new Player(username, ctx);
            ServerHandler.connectedClients.put(username, player);
            PlayerHandler.updatePlayers(username, true);
            PacketManager.packetHandler[Constants.SEND_TABLES_PACKET].handle(ctx, packet);
        }
    }
}
