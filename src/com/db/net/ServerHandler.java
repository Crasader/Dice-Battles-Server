/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.net;

import com.db.net.packet.PacketManager;
import com.db.player.Player;
import com.db.player.PlayerHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 *
 * @author Amjad
 */
public class ServerHandler extends SimpleChannelUpstreamHandler {

    public static HashMap<String, Player> connectedClients = new HashMap<String, Player>();

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) {
        String username = null;
        for (Iterator iter = ServerHandler.connectedClients.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry) iter.next();
            Player player = (Player) entry.getValue();
            username = player.username;
            if (player.session.getAttachment() == ctx.getAttachment()) {
                connectedClients.remove(username);
                break;
            }
        }
        PlayerHandler.updatePlayers(username, false);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        final ArrayList<Object> packet = (ArrayList<Object>) e.getMessage();
        if (packet == null) {
            return;
        }
        PacketManager.handle(ctx, packet);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getCause().printStackTrace();
    }
}