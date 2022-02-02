package org.example.productV2;

import java.sql.*;
import java.util.ArrayList;

public class StateManager {
    static {
        try {
            // This is where we load the driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load Driver Class");
        }
    }

    static public ArrayList<Goods> goodsByIDcompConnection(Company c) {
        ArrayList<Goods> goods = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Student\\Documents\\Smyslov\\x.accdb", "", "");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM goods WHERE idComp =" + c.id + ";");

            while (rs.next()) {
                Goods g = new Goods(rs.getInt("id"), rs.getString("name"), rs.getInt("idComp"), rs.getInt("price"));
                goods.add(g);
            }
            rs.close();
            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //return goods.toArray(new Goods[0]);
        return goods;
    }

    static public ArrayList<Company> listCompConnection() {
        ArrayList<Company> companies = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Student\\Documents\\Smyslov\\x.accdb", "", "");
            Statement statement = connection.createStatement();
            String Sql = "SELECT * FROM company;";
            ResultSet rs = statement.executeQuery(Sql);
            while (rs.next()) {
                Company c = new Company(rs.getInt("id"), rs.getString("name"));
                companies.add(c);
            }
            rs.close();
            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return companies;
    }

    public static void deleteGoods(int id) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Student\\Documents\\Smyslov\\x.accdb", "", "");
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM goods WHERE id = " + id + ";");
        connection.close();
        statement.close();
    }

    public static void addGoods(int idComp, String name, int price) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Student\\Documents\\Smyslov\\x.accdb", "", "");
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO goods(idComp, name, amount, price) VALUES " + idComp + ", " + name + ", " + price + ";");
            //String sql = "SELECT goods.id FROM goods WHERE goods.s_date = '" + g.sDate + "';";
            //ResultSet rs = statement.executeQuery(sql);
            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addGoodsName(String name) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Student\\Documents\\Smyslov\\x.accdb", "", "");
            Statement statement = connection.createStatement();
            statement.executeUpdate(" INSERT INTO goods(name) VALUES(" + "'" + name + "')");
            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addGoodsPrice(int price) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Student\\Documents\\Smyslov\\x.accdb", "", "");
            Statement statement = connection.createStatement();
            statement.executeUpdate(" INSERT INTO goods(price) VALUES(" + "'" + price + "')");
            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
//    public void updateDb(ArrayList<String> sqlList) throws SQLException {
//        Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Student\\Documents\\Smyslov\\x.accdb", "", "");
//        Statement statement = connection.createStatement();
//
//        for(String sql : sqlList) {
//            statement.executeUpdate(sql);
//        }
//    }
}
