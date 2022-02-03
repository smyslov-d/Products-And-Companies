package org.swing.productV3.hibernate.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Company entity
 */
@Entity
@Table(name = "company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idcomp")
    private List<Product> productsList = new ArrayList<>();

    @Override
    public String toString() {
        return name;
    }
}
