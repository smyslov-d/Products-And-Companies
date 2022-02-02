package org.example.productV2;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainForm extends JFrame {
    private int state;

    private JPanel mainPanel;
    private JPanel southPanel;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JTable table;
    private JPanel tablePanel;
    private JScrollPane scroll;
    private JTextField setCompField;
    private JPanel textPanel;
    private JPanel btnPanel;
    private JButton prevBtn;
    private JButton nextBtn;
    private JButton addBtn;
    private JButton deleteBtn;
    private JTable tableCompany;

    private void setContent() {
        ArrayList<Company> companies = new ArrayList<>();
        companies = StateManager.listCompConnection();
        //table.setDefaultRenderer(Object.class, new MyTableRenderer());
        try {
            Company c = companies.get(state);
            setCompField.setText(c.name);
            table.setModel(new GoodsModel(c));
        } catch (IndexOutOfBoundsException e) {
            state--;
            nextBtn.setEnabled(false);
        }
        deleteBtn.setEnabled(true);
    }

    private void setContent(int i) throws SQLException {
        ArrayList<Company> companies = new ArrayList<>();
        companies = StateManager.listCompConnection();
        //table.setDefaultRenderer(Object.class, new MyTableRenderer());

        try {
            Company c = companies.get(state);
            setCompField.setText(c.name);
            table.setModel(new GoodsModel(c, 1));
        } catch (IndexOutOfBoundsException e) {
            state--;
            nextBtn.setEnabled(false);
        }
        deleteBtn.setEnabled(true);
    }

    public MainForm() {
        super("Проект Х");
        setContentPane(mainPanel);

        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Операции");
        JMenu m2 = new JMenu("Справка");
        mb.add(m1);
        mb.add(m2);

        JMenu menuLoadDataBase = new JMenu("Загрузить");

        m1.add(menuLoadDataBase);

        JMenuItem menuSearch = new JMenuItem("Искать");
        JMenuItem menuExit = new JMenuItem("Выход");

        JMenuItem menuLoadAllCompany = new JMenuItem("Компании");
        JMenuItem menuGuide = new JMenuItem("Просмотреть справку");
        JMenuItem menuReport = new JMenuItem("Отправить отзыв");
        JMenuItem menuInfo = new JMenuItem("О программе");

        m1.add(menuSearch);
        m1.add(menuExit);

        m2.add(menuGuide);
        m2.add(menuReport);
        m2.add(menuInfo);

        menuLoadDataBase.add(menuLoadAllCompany);

        mainPanel.add(BorderLayout.NORTH, mb);

        deleteBtn.setEnabled(false);

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int d = (int) table.getValueAt(table.getSelectedRow(), 0);
                try {
                    StateManager.deleteGoods(d);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                setContent();
            }
        });

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setContent(1);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                //логика для работы с Table сохранения записи
//
//                    String name = (String) table.getValueAt(table.getSelectedRow(), 0);
//                    int amount = (int) table.getValueAt(table.getSelectedRow(), 1);
//                    int price = (int) table.getValueAt(table.getSelectedRow(), 2);
//                    StateManager.addGoods(state, name, amount, price); //updataDb 17 47  20 08  21 53
//                    //GoodsModel.updateDb(goods);
            }
        });

        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prevBtn.setEnabled(true);
                state = (state + 1);
                setContent();
            }
        });

        prevBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (state != 0) {
                    nextBtn.setEnabled(true);
                    state = (state - 1);
                } else {
                    prevBtn.setEnabled(false);
                }
                setContent();
            }
        });

        menuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        menuLoadAllCompany.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContent();
            }
        });

        menuGuide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = "Информация";
                String text = "Чтобы загрузить базу нажмите \"Операции\" , затем нажмите \"Загрузить\"";
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
                String text = "Проект \"Х\" \nВерсия 2.0. Все права защищены.\nAdmin Dima";
                JOptionPane.showMessageDialog(null, text, title, JOptionPane.PLAIN_MESSAGE);
            }
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
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
        southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        prevBtn = new JButton();
        prevBtn.setText("<");
        southPanel.add(prevBtn);
        nextBtn = new JButton();
        nextBtn.setText(">");
        southPanel.add(nextBtn);
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        centerPanel.add(btnPanel, new GridConstraints(0, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addBtn = new JButton();
        addBtn.setText("Add");
        btnPanel.add(addBtn, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteBtn = new JButton();
        deleteBtn.setText("Delete");
        deleteBtn.setToolTipText("Удалить");
        btnPanel.add(deleteBtn, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textPanel = new JPanel();
        textPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        centerPanel.add(textPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        setCompField = new JTextField();
        setCompField.setBackground(new Color(-16777216));
        setCompField.setEditable(false);
        setCompField.setEnabled(false);
        setCompField.setForeground(new Color(-16777216));
        setCompField.setToolTipText("Имя Компании");
        textPanel.add(setCompField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        centerPanel.add(tablePanel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        scroll = new JScrollPane();
        tablePanel.add(scroll, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table = new JTable();
        scroll.setViewportView(table);
        northPanel = new JPanel();
        northPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(northPanel, BorderLayout.NORTH);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}