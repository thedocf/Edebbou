/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author X
 */
public class MyConnection {

    //classs nous servant comme connextion a la base 
    Connection cnx;
   private static MyConnection instance;

    private MyConnection() {
        try {
            String url = "jdbc:mysql://localhost/sandyplan"; //nomapi:nomsgbd
            String login = "root";
            String pwd = "";

            cnx = DriverManager.getConnection(url, login, pwd); //import from java.sql.connection , ici suseptible de generer exeption => try catch
            System.out.println("Connection etablie");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public Connection getCnx() {
        return cnx;
    }

    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();

        }

        return instance;
    }

}
