/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.net.packet.impl;

import com.db.net.packet.PacketBuilder;
import com.db.net.packet.PacketHandler;
import com.db.player.PlayerManager;
import com.db.util.Constants;
import java.util.ArrayList;
import org.jboss.netty.channel.ChannelHandlerContext;

/**
 *
 * @author Amjad
 */
public class RegisterPacket implements PacketHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, ArrayList<Object> packet) {
        final String username = packet.get(0).toString();
        final String password = packet.get(1).toString();
        final String secretCode = packet.get(2).toString();
        final PacketBuilder pb = new PacketBuilder(Constants.REGISTER_PACKET);
        pb.add(PlayerManager.register(username, password, secretCode));
        ctx.getChannel().write(pb.getPacket());
    }
}
