/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.net.packet;

import java.util.ArrayList;

/**
 *
 * @author Amjad
 */
public class PacketBuilder {

    private final ArrayList<Object> packet = new ArrayList<Object>();

    public PacketBuilder(int id) {
        packet.add(id);
    }

    public void add(Object object) {
        packet.add(object);
    }

    public ArrayList<Object> getPacket() {
        return packet;
    }
}
