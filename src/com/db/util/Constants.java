/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.util;

/**
 *
 * @author Amjad
 */
public interface Constants {

    final int PORT = 1210;
    
    final int LOGIN_PACKET = 0;
    final int REGISTER_PACKET = 1;
    final int PUBLIC_MESSAGE_PACKET = 2;
    final int PRIVATE_MESSAGE_PACKET = 3;
    final int PLAYER_UPDATE_PACKET = 4;
    final int CREATE_TABLE_PACKET = 5;
    final int SEND_TABLES_PACKET = 6;
    final int JOIN_TABLE_PACKET = 7;
    final int NEW_USER_JOINED_PACKET = 8;
}
