package org.example.poductV1;

import javax.swing.*;

public class SwingMain {
    public static void main(String args[]) {
        //Creating the form
        MainForm form = new MainForm();
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setSize(600, 400);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }
}
