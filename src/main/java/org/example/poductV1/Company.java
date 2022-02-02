package org.example.poductV1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    int id;
    double capacity;
    String name;
    public List<Goods> goodsList = new ArrayList<>();

    public Company(int id, double capacity, String name){
        this.id = id;
        this.capacity = capacity;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id &&
                Double.compare(company.capacity, capacity) == 0 &&
                Objects.equals(name, company.name) &&
                Objects.equals(goodsList, company.goodsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, capacity, name, goodsList);
    }

    public String getName() {
        return name;
    }
}
