package org.swing.productV3.hibernate;

import java.sql.SQLException;

/*
 * My exception
 */
public class SystemProblemException extends RuntimeException {
    public SystemProblemException(SQLException throwables) {
        super("System is fault. Problem is occured.");
        throwables.printStackTrace();
    }
}
