/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.player;

import java.util.ArrayList;
import org.jboss.netty.channel.ChannelHandlerContext;

/**
 *
 * @author Amjad
 */
public class Player {

    public final String username;
    public final ChannelHandlerContext session;

    public Player(String username, ChannelHandlerContext session) {
        this.username = username;
        this.session = session;
    }

    public void write(ArrayList<Object> packet) {
        session.getChannel().write(packet);
    }
}
