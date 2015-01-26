/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.net.packet.impl;

import com.db.net.packet.PacketBuilder;
import com.db.net.packet.PacketHandler;
import com.db.util.Constants;
import java.util.ArrayList;
import org.jboss.netty.channel.ChannelHandlerContext;

/**
 *
 * @author Amjad
 */
public class SendTablesPacket implements PacketHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, ArrayList<Object> packet) {
        final PacketBuilder pb = new PacketBuilder(Constants.SEND_TABLES_PACKET);
        for (int i = 0; i < CreateTablePacket.tables.size(); i++) {
            pb.add(CreateTablePacket.tables.get(i));
        }
        ctx.getChannel().write(pb.getPacket());
    }
}
