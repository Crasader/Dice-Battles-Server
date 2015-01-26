package com.db.net.packet;

import com.db.net.packet.impl.RegisterPacket;
import com.db.net.packet.impl.SendTablesPacket;
import com.db.net.packet.impl.LoginPacket;
import com.db.net.packet.impl.CreateTablePacket;
import com.db.net.packet.impl.JoinTablePacket;
import com.db.net.packet.impl.PrivateMessagePacket;
import com.db.net.packet.impl.PublicMessagePacket;
import com.db.util.Constants;
import java.util.ArrayList;
import org.jboss.netty.channel.ChannelHandlerContext;

public class PacketManager {

    public static final int TOTAL_PACKETS = 10;
    public static final PacketHandler[] packetHandler = new PacketHandler[TOTAL_PACKETS];

    static {
        packetHandler[Constants.LOGIN_PACKET] = new LoginPacket();
        packetHandler[Constants.REGISTER_PACKET] = new RegisterPacket();
        packetHandler[Constants.PUBLIC_MESSAGE_PACKET] = new PublicMessagePacket();
        packetHandler[Constants.PRIVATE_MESSAGE_PACKET] = new PrivateMessagePacket();
        packetHandler[Constants.CREATE_TABLE_PACKET] = new CreateTablePacket();
        packetHandler[Constants.SEND_TABLES_PACKET] = new SendTablesPacket();
        packetHandler[Constants.JOIN_TABLE_PACKET] = new JoinTablePacket();
    }

    public static void handle(ChannelHandlerContext ctx, ArrayList<Object> packet) {
        final int opcode = Integer.parseInt(packet.get(0).toString());
        packet.remove(0);
        packetHandler[opcode].handle(ctx, packet);
    }
}