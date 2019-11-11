/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hung1
 */
public class ConnectDB {
    public static Connection getConnectionDB() throws ClassNotFoundException,
            SQLException{
        String hostDB = "localhost";
        String dbName = "ltm_file";
        String username = "root";
        String password = "123";
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        String connectionURL = "jdbc:mysql://" + hostDB + ":3306/" + dbName;
        
        Connection con = DriverManager.getConnection(connectionURL,
                username, password);
        return con;
    }
}
