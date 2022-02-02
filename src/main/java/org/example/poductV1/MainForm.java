package org.example.poductV1;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MainForm extends JFrame {

    private JPanel mainPanel;
    private JPanel gridPanel;
    private JPanel btnSouthPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JScrollPane scrollPanel;
    private JTable table;
    private JPanel txtWestPanel;
    private JButton nextBtn;
    private JButton prevBtn;
    private JButton deleteBtn;
    private JButton addBtn;
    private JSeparator separator;
    private JTextField nameCompField;
    private JTextField capacityCompField;
    private JTextField urlCompField;
    private JLabel urlCompLabel;
    private JLabel capacityCompLabel;
    private JLabel nameCompLabel;

    public MainForm() {
        super("Таблица");

        setContentPane(mainPanel);

        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Операции");
        JMenu m2 = new JMenu("Справка");
        mb.add(m1);
        mb.add(m2);

        JMenuItem menuLoadAllGoods = new JMenuItem("Загрузить все товары");
        JMenuItem menuLoadAllCompany = new JMenuItem("Загрузить всех продавцов");
        JMenuItem menuSearch = new JMenuItem("Искать");
        JMenuItem menuExit = new JMenuItem("Выход");

        JMenuItem menuGuide = new JMenuItem("Просмотреть справку");
        JMenuItem menuReport = new JMenuItem("Отправить отзыв");
        JMenuItem menuInfo = new JMenuItem("О программе");

        m1.add(menuLoadAllGoods);
        m1.add(menuLoadAllCompany);
        m1.add(menuSearch);
        m1.add(menuExit);

        m2.add(menuGuide);
        m2.add(menuReport);
        m2.add(menuInfo);

        mainPanel.add(BorderLayout.NORTH, mb);

        menuGuide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = "Информация";
                String text = "Чтобы загрузить базу нажмите \"Операции, затем нажмите \"Загрузить\"";
                JOptionPane.showMessageDialog(null, text, title, JOptionPane.INFORMATION_MESSAGE);
            }
        });

        menuReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = "Перейти на сайт разработчика?";
                JOptionPane.showConfirmDialog(null, text);
            }
        });

        menuInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = "Сведения";
                String text = "Версия 1.0. Все права защищены. Admin D";
                JOptionPane.showMessageDialog(null, text, title, JOptionPane.PLAIN_MESSAGE);
            }
        });

        menuLoadAllGoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table.setModel(new AbstractTableModel() {
                        StateManager stateManager = new StateManager();

                        @Override
                        public int getRowCount() {
                            return stateManager.goods.size();
                        }

                        @Override
                        public int getColumnCount() {
                            return 3;
                        }

                        @Override
                        public Object getValueAt(int rowIndex, int columnIndex) {
                            Goods g = stateManager.getGoods()[rowIndex];
                            switch (columnIndex) {
                                case 0: {
                                    return g.name;
                                    // return g.company.getName();
                                }
                                case 1: {
                                    return g.price;
                                }
                                case 2: {
                                    return g.amount;
                                }
                            }
                            return null;
                        }
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


        menuLoadAllCompany.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table.setModel(new AbstractTableModel() {
                        StateManager stateManager = new StateManager();

                        @Override
                        public int getRowCount() {
                            return stateManager.company.size();
                        }

                        @Override
                        public int getColumnCount() {
                            return 2;
                        }

                        @Override
                        public Object getValueAt(int rowIndex, int columnIndex) {
                            Company company = stateManager.getCompany()[rowIndex];
                            switch (columnIndex) {
                                case 0: {
                                    return company.name;
                                }
                                case 1: {
                                    return company.id;
                                }
                            }
                            return null;
                        }
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        menuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == searchButton) {
                    System.out.println("search all shit ");
                    String searchFieldText = searchField.getText();
                    System.out.println(searchFieldText);
                }
            }
        });
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[]{nameCompField.getText(), capacityCompField.getText(), urlCompField.getText()});
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.removeRow(table.getSelectedRow());
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        gridPanel = new JPanel();
        gridPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        scrollPanel = new JScrollPane();
        gridPanel.add(scrollPanel, BorderLayout.CENTER);
        table = new JTable();
        scrollPanel.setViewportView(table);
        btnSouthPanel = new JPanel();
        btnSouthPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        mainPanel.add(btnSouthPanel, BorderLayout.SOUTH);
        searchField = new JTextField();
        Font searchFieldFont = this.$$$getFont$$$(null, -1, -1, searchField.getFont());
        if (searchFieldFont != null) searchField.setFont(searchFieldFont);
        searchField.setHorizontalAlignment(2);
        searchField.setMinimumSize(new Dimension(60, 30));
        searchField.setPreferredSize(new Dimension(100, 30));
        btnSouthPanel.add(searchField);
        searchButton = new JButton();
        searchButton.setText("Искать");
        btnSouthPanel.add(searchButton);
        separator = new JSeparator();
        separator.setEnabled(true);
        separator.setOrientation(1);
        btnSouthPanel.add(separator);
        prevBtn = new JButton();
        prevBtn.setText("Prev");
        btnSouthPanel.add(prevBtn);
        nextBtn = new JButton();
        nextBtn.setText("Next");
        btnSouthPanel.add(nextBtn);
        addBtn = new JButton();
        addBtn.setText("Add");
        btnSouthPanel.add(addBtn);
        deleteBtn = new JButton();
        deleteBtn.setText("Delete");
        btnSouthPanel.add(deleteBtn);
        txtWestPanel = new JPanel();
        txtWestPanel.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(txtWestPanel, BorderLayout.WEST);
        final Spacer spacer1 = new Spacer();
        txtWestPanel.add(spacer1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        nameCompField = new JTextField();
        nameCompField.setEditable(true);
        nameCompField.setEnabled(true);
        txtWestPanel.add(nameCompField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        capacityCompField = new JTextField();
        capacityCompField.setEditable(true);
        capacityCompField.setEnabled(true);
        txtWestPanel.add(capacityCompField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        urlCompField = new JTextField();
        urlCompField.setEditable(true);
        urlCompField.setEnabled(true);
        txtWestPanel.add(urlCompField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        nameCompLabel = new JLabel();
        nameCompLabel.setText("Компания");
        txtWestPanel.add(nameCompLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        capacityCompLabel = new JLabel();
        capacityCompLabel.setText("Капитализация");
        txtWestPanel.add(capacityCompLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        urlCompLabel = new JLabel();
        urlCompLabel.setText("Сайт");
        txtWestPanel.add(urlCompLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
