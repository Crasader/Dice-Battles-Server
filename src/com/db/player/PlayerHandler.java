/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.player;

import com.db.net.ServerHandler;
import com.db.net.packet.PacketBuilder;
import com.db.util.Constants;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Amjad
 */
public class PlayerHandler {
    
    public static void updatePlayers(String username, boolean add) {
        final PacketBuilder pb = new PacketBuilder(Constants.PLAYER_UPDATE_PACKET);
        pb.add(add);
        pb.add(username);
        for (Iterator iter = ServerHandler.connectedClients.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry) iter.next();
            Player player = (Player) entry.getValue();
            player.write(pb.getPacket());            
        }
    }
}
