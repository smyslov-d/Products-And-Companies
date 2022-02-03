package org.swing.productV3.jdbs.entity;

/*
 * Product entity
 */
public class ProductsDAOEntity {
    public int id;
    public String name;
    public int idComp;
    public int price;

    public ProductsDAOEntity(int id, String name, int idComp, int price) {
        this.id = id;
        this.name = name;
        this.idComp = idComp;
        this.price = price;
    }

    public ProductsDAOEntity(String name, int idComp, int price) {
        this.name = name;
        this.idComp = idComp;
        this.price = price;
    }
}
