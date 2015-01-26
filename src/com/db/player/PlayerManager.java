/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.player;

import com.db.net.ServerHandler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Amjad
 */
public class PlayerManager {

    public static final String PATH = "./data/users/";

    public static int load(String username, String password) {
        BufferedReader user = null;
        try {
            user = new BufferedReader(new FileReader(PATH + username + ".txt"));
        } catch (FileNotFoundException ignored) {
            return 0;
        }

        if (ServerHandler.connectedClients.containsKey(username)) {
            return 1;
        }

        String line = null;
        try {
            while ((line = user.readLine()) != null) {
                if (line.contains("password: ")) {
                    if (!line.replace("password: ", "").equals(password)) {
                        return 0;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 2;
    }

    public static int register(String username, String password, String secretCode) {
        if (new File(PATH + username + ".txt").exists()) {
            return 0;
        }

        try {
            BufferedWriter user = new BufferedWriter(new FileWriter(PATH + username + ".txt"));
            user.write("password: " + password);
            user.newLine();
            user.write("secretcode: " + secretCode);
            user.newLine();
            user.write("avatar: null");
            user.newLine();
            user.write("credit: 0.00");
            user.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 1;
    }
}
