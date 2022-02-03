package org.swing.productV3.jdbs.entity;

/*
 * Company entity
 */
public class CompanyDAOEntity {
    public int id;
    public String name;

    public CompanyDAOEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CompanyDAOEntity( String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
