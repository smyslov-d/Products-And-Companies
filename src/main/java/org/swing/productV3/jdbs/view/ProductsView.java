package org.swing.productV3.jdbs.view;

import org.swing.productV3.jdbs.controller.Controller;
import org.swing.productV3.jdbs.entity.CompanyDAOEntity;
import org.swing.productV3.jdbs.entity.ProductsDAOEntity;
import org.swing.productV3.jdbs.model.CompaniesListModel;
import org.swing.productV3.jdbs.model.ProductsTableModel;
import org.swing.productV3.form.CompanyAddForm;
import org.swing.productV3.form.MainForm;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableColumn;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/*
 * All activity and relations with other classes
 */
public class ProductsView {
    public MainForm form = new MainForm();
    public CompanyAddForm companyAddForm;
    public Controller controller = new Controller();
    public CompaniesListModel companiesListModel;
    public ProductsTableModel productsTableModel;
    public int indexCompany;

    /*
     * Constructor which listeners
     */
    public ProductsView() {
        form.addCompBtn.addActionListener(e -> {
            companyAddForm = new CompanyAddForm();
            companyAddForm.addBtn.addActionListener(e1 -> addCompany());
        });

        form.jListComp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Get index element
                    indexCompany = form.jListComp.locationToIndex(e.getPoint());
                    loadProducts(indexCompany);
                }
            }
        });

        form.addProdBtn.addActionListener(e -> addEmptyRow());
        form.deleteCompBtn.addActionListener(e -> deleteCompany());
        form.deleteProdBtn.addActionListener(e -> deleteProduct());
        form.menuExit.addActionListener(e -> exit());
        form.menuGuide.addActionListener(e -> guide());
        form.menuReport.addActionListener(e -> report());
        form.menuInfo.addActionListener(e -> info());
        form.menuLoad.addActionListener(e -> loadCompanies());

        form.table.getDefaultEditor(Object.class).addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                ProductsDAOEntity p = productsTableModel.getProductsList(form.table.getSelectedRow());
                if (p.id > 0) {
                    controller.update(p);
                } else {
                    controller.add(p);
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e) {
            }
        });
    }

    /*
     * Add Company and refresh (List, Table)
     */
    public void addCompany() {
        String name = companyAddForm.compNameTxtFld.getText();
        if (name.length() > 255) {
            addCompImpossible();
            companyAddForm.compNameTxtFld.setText("");
        } else {
            CompanyDAOEntity companyDAOEntity = new CompanyDAOEntity(name);
            controller.add(companyDAOEntity);
            addCompComplited();
            indexCompany = -1;
            loadCompanies();
            refreshTable(indexCompany);
        }
    }

    /*
     * Add empty row at Table
     */
    public void addEmptyRow() {
        CompanyDAOEntity c = (CompanyDAOEntity) companiesListModel.getElementAt(indexCompany);
        ProductsDAOEntity productsDAOEntity = new ProductsDAOEntity("", c.id, 0);
        productsTableModel.addProduct(productsDAOEntity);
        productsTableModel.fireTableRowsInserted(productsTableModel.getRowCount(), productsTableModel.getColumnCount());
        hideColumn("ID");
        unblockTableButtons();
    }

    /*
     * Delete Company by id and refresh (List, Table)
     */
    public void deleteCompany() {
        CompanyDAOEntity c = (CompanyDAOEntity) form.jListComp.getSelectedValue();
        controller.deleteCompany(c);
        indexCompany = -1;
        loadCompanies();
        refreshTable(indexCompany);
    }

    /*
     * Delete Product by id and refresh Table
     */
    public void deleteProduct() {
        ProductsDAOEntity p = productsTableModel.getProductsList(form.table.getSelectedRow());
        controller.deleteProduct(p);
        refreshTable(indexCompany);
    }

    /*
     * Load all Products at Table by id Company
     */
    public void loadProducts(int index) {
        List<CompanyDAOEntity> companiesList = controller.getAllCompanies();
        CompanyDAOEntity companyDAOEntity = companiesList.get(index);

        List<ProductsDAOEntity> productsList = controller.getProductsByCompany(companyDAOEntity);
        productsTableModel = new ProductsTableModel(productsList);
        form.table.setModel(productsTableModel);
        hideColumn("ID");
        unblockTableButtons();
    }

    /*
     * Load all Companies at list
     */
    public void loadCompanies() {
        List<CompanyDAOEntity> companiesList = controller.getAllCompanies();
        companiesListModel = new CompaniesListModel(companiesList);
        form.jListComp.setModel(companiesListModel);
        unblockListButtons();
    }

    /*
     * Refresh Table
     */
    public void refreshTable(int indexCompany) {
        if (indexCompany >= 0) {
            loadProducts(indexCompany);
        } else {
            List<ProductsDAOEntity> empty = new ArrayList<>();
            ProductsTableModel emptyModel = new ProductsTableModel(empty);
            form.table.setModel(emptyModel);
            hideColumn("ID");
            blockTableButtons();
        }
    }

    /*
     * Block Table buttons
     */
    public void blockTableButtons() {
        form.addProdBtn.setEnabled(false);
        form.deleteProdBtn.setEnabled(false);
    }

    /*
     * Unblock Table buttons
     */
    public void unblockTableButtons() {
        form.addProdBtn.setEnabled(true);
        form.deleteProdBtn.setEnabled(true);
    }

    /*
     * Unblock List buttons
     */
    public void unblockListButtons() {
        form.addCompBtn.setEnabled(true);
        form.deleteCompBtn.setEnabled(true);
    }

    /*
     * Hide column at table
     */
    public void hideColumn(String name) {
        TableColumn col = form.table.getColumn(name);
        col.setMinWidth(0);
        col.setMaxWidth(0);
        col.setWidth(0);
    }

    /*
     * Show dialog: add company complited
     */
    public void addCompComplited() {
        String title = "Информация";
        String text = "Компания успешно добавлена";
        JOptionPane.showMessageDialog(null, text, title, JOptionPane.PLAIN_MESSAGE);
    }

    /*
     * Show dialog: impossible add company
     */
    public void addCompImpossible() {
        String title = "Невозможно добавить компанию";
        String text = "Слишком большое имя\nПопробуйте еще раз";
        JOptionPane.showMessageDialog(null, text, title, JOptionPane.PLAIN_MESSAGE);
    }

    /*
     * Close application
     */
    public void exit() {
        System.exit(0);
    }

    /*
     * Show dialog guide
     */
    public void guide() {
        String title = "Информация";
        String text = "Чтобы загрузить базу нажмите \"Операции\" , затем нажмите \"Загрузить\"";
        JOptionPane.showMessageDialog(null, text, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /*
     * Show dialog report
     */
    public void report() {
        String text = "Перейти на сайт разработчика?";
        JOptionPane.showConfirmDialog(null, text);
    }

    /*
     * Show dialog info
     */
    public void info() {
        String title = "Сведения";
        String text = "Проект \"Y\" \nВерсия 3.0. Все права защищены.\nAdmin Dima";
        JOptionPane.showMessageDialog(null, text, title, JOptionPane.PLAIN_MESSAGE);
    }
}