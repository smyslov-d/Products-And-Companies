package org.swing.productV3.hibernate.model;

import org.swing.productV3.hibernate.entity.Company;

import javax.swing.*;
import java.util.List;

/*
 * Model where represent list all Companies
 */
public class CompaniesListModel extends AbstractListModel {
    private List<Company> companiesList;

    /*
     * Constructor
     */
    public CompaniesListModel(List<Company> companiesList) {
        this.companiesList = companiesList;
    }

    /*
     * Size list
     */
    @Override
    public int getSize() {
        return companiesList.size();
    }

    /*
     * Represent these elements at list
     */
    @Override
    public Object getElementAt(int index) {
        return companiesList.get(index);
    }
}
