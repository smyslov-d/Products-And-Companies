package org.swing.productV3.jdbs.model;

import org.swing.productV3.jdbs.entity.ProductsDAOEntity;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/*
 * Model where represents products by company
 */
public class ProductsTableModel extends AbstractTableModel {
    private final List<ProductsDAOEntity> productsList;

    /*
     * Constructor
     */
    public ProductsTableModel(List<ProductsDAOEntity> productsList) {
        this.productsList = productsList;
    }

    public void addProduct(ProductsDAOEntity p) {
        productsList.add(p);
    }

    public ProductsDAOEntity getProductsList(int index) {
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
        ProductsDAOEntity p = productsList.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return p.id;
            }
            case 1: {
                return p.name;
            }
            case 2: {
                return p.price;
            }
        }
        return null;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ProductsDAOEntity p = productsList.get(rowIndex);
        switch (columnIndex) {
            case 1: {
                p.name = ((String) aValue);
                break;
            }
            case 2: {
                try {
                    p.price = (Integer.parseInt((String) aValue));
                } catch (NumberFormatException e) {
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