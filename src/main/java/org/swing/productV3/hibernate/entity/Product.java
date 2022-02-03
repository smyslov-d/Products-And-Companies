package org.swing.productV3.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/*
 * Product entity
 */
@Entity
@Table(name = "goods")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idcomp")
    private Company idcomp;

    private int price;
    private String name;

    public Product(Company company) {
        this.idcomp = company;
    }
}
