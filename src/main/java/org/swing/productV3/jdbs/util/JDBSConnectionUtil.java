package org.swing.productV3.jdbs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Connection with database
 */
public class JDBSConnectionUtil {
    public static final String DB_DRIVER = "net.ucanaccess.jdbc.UcanaccessDriver";
    public static final String URL = "jdbc:ucanaccess://C:\\Users\\Student\\IdeaProjects\\ProductsAndCompany\\x.accdb";
    public static final String USER = "";
    public static final String PASSWORD = "";

    /*
     * Create Connection
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            // This is where we load the driver
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERROR");
        }
        return connection;
    }
}
