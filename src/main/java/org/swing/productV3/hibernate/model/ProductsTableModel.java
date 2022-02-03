package org.swing.productV3.hibernate.model;

import org.swing.productV3.hibernate.entity.Product;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/*
 * Model where represents products by company
 */
public class ProductsTableModel extends AbstractTableModel {
    private final List<Product> productsList;

    /*
     * Constructor
     */
    public ProductsTableModel(List<Product> productsList) {
        this.productsList = productsList;
    }

    public void addProduct(Product p) {
        productsList.add(p);
    }

    public Product getProductsList(int index) {
        return productsList.get(index);
    }

    @Override
    public int getRowCount() {
        return productsList.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "ID";
            }
            case 1: {
                return "Name";
            }
            case 2: {
                return "Price";
            }
        }
        return super.getColumnName(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product p = productsList.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return p.getId();
            }
            case 1: {
                return p.getName();
            }
            case 2: {
                return p.getPrice();
            }
        }
        return null;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Product p = productsList.get(rowIndex);
        switch (columnIndex) {
            case 1: {
                p.setName((String) aValue);
                break;
            }
            case 2: {
                try {
                    p.setPrice(Integer.parseInt((String) aValue));
                } catch (java.lang.NumberFormatException e) {
                    break;
                }
                break;
            }
            default:
                throw new RuntimeException("Column index is incorrect: " + columnIndex);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}