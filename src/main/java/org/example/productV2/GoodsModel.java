package org.example.productV2;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class GoodsModel extends AbstractTableModel {
    private ArrayList<Goods> goods;
    private int size;
    private Company company;

    public GoodsModel(Company c) {
        goods = StateManager.goodsByIDcompConnection(c);
        size = goods.size();
        company = c;
    }

    public GoodsModel(Company c, int i) {
        goods = StateManager.goodsByIDcompConnection(c);
        Goods g = new Goods();
        goods.add(g);

        size = goods.size();
    }
//    public ArrayList<Goods> addRow() {
//        Goods g = new Goods(0,null,0,0,0);
//        goods.add(g);
//        return goods;
//    }

    @Override
    public int getRowCount() {
        return size;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
//            case 0: {
//                return "ID";
//            }
            case 0: {
                return "Name";
            }

            case 1: {
                return "Price";
            }
        }
        return super.getColumnName(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Goods g = goods.get(rowIndex);
        switch (columnIndex) {
//            case 0: {
//                return g.id;
//            }
            case 0: {
                return g.name;
            }
            case 1: {
                return g.price;
            }
        }
        return null;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Goods g = goods.get(rowIndex);
        switch (columnIndex) {
//            case 0: {
//                g.id = Integer.parseInt((String) aValue);
//                break;
//            }
            case 0: {
                g.name = (String) aValue;
                if (g.id == 0) {
                    StateManager.addGoodsName(g.name);
                }
                break;
            }
            case 1: {
                g.price = Integer.parseInt((String) aValue);
                StateManager.addGoodsPrice(g.price);
                break;
            }
            default:
                throw new RuntimeException("Column index is incorrect: " + columnIndex);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
//        if (columnIndex == 0) {
//            return false;
//        }
        return true;
    }


//    public ArrayList updateDb(String tableName) {
//
//        ArrayList<String> sqlList = new ArrayList<>();
//
//        for (int i = 0; i < goods.length; i++) {
//            Goods g = goods[i];
//            sqlList.add("update " + tableName + "set name='" + goods[1] + "', amount='" + goods[2] + "', price='" + goods[3] + "' where id=" + goods[0] + ";");
//        }
//        return sqlList;
//
//    }
}
