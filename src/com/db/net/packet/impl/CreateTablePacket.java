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
public class CreateTablePacket implements PacketHandler {

    public final static ArrayList<Object[]> tables = new ArrayList<Object[]>();

    @Override
    public void handle(ChannelHandlerContext ctx, ArrayList<Object> packet) {
        final PacketBuilder pb = new PacketBuilder(Constants.CREATE_TABLE_PACKET);
        final String name = packet.get(0).toString();
        int response = 1;
        for (int i = 0; i < tables.size(); i++) {
            if (tables.get(i)[0].equals(name)) {
                response = 0;
                break;
            }
        }
        pb.add(response);
        ctx.getChannel().write(pb.getPacket());
        if (response == 1) {
            tables.add(new Object[]{name, Integer.parseInt(packet.get(1).toString()), "1/" + Integer.parseInt(packet.get(2).toString())});
        }
    }
}
