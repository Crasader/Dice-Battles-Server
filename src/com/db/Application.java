/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db;

import com.db.net.Server;

/**
 *
 * @author Amjad
 */
public class Application {

    public static void main(String[] args) {
        Server.bind();
        System.out.println("Dice Battles - Online");
    }
}
