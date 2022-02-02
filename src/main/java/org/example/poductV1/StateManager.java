package org.example.poductV1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StateManager {
    public List<Company> company = new ArrayList<>();
    public List<Goods> goods = new ArrayList<>();

    public Goods[] getGoods() {
        return goods.toArray(new Goods[0]);
    }
    public Company[] getCompany() {
        return company.toArray(new Company[0]);
    }

    public static void main(String[] args) throws SQLException {
        StateManager manager = new StateManager();
        for(Company company: manager.company){
            System.out.println(company.getName());

        }
    }

    public StateManager() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Student\\Documents\\Smyslov\\db.accdb", "", "");
        Statement stmt = con.createStatement();

        ResultSet rsComp = stmt.executeQuery("SELECT * FROM Company");
        while (rsComp.next()) {
            Company c = new Company(rsComp.getInt("id"), rsComp.getDouble("capacity"), rsComp.getString("name"));
            company.add(c);
        }

        ResultSet rsGoods = stmt.executeQuery("SELECT * FROM Goods");
        while (rsGoods.next()) {
            Goods g = new Goods(rsGoods.getInt("id"), rsGoods.getInt("idComp"), rsGoods.getInt("amount"), rsGoods.getDouble("price"), rsGoods.getString("name"));
            goods.add(g);
        }

        for (Goods g : goods) {
            for (Company company1: company)
                if(g.getIdComp()==company1.getId()) {
                    g.setCompany(company1);
                    company1.goodsList.add(g);
                }
        }
    }
}



