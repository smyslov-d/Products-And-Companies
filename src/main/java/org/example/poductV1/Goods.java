package org.example.poductV1;

public class Goods {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdComp() {
        return idComp;
    }

    public void setIdComp(int idComp) {
        this.idComp = idComp;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    int id;
    int idComp;
    int amount;
    double price;
    String name;
    public Company company;

    public Goods(int id, int idComp, int amount, double price, String name) {
        this.id = id;
        this.idComp = idComp;
        this.amount = amount;
        this.price = price;
        this.name = name;
    }

}
