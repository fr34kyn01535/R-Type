package de.hn.vhauryn.rtype.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Helper {

    public static JFrame createJFrame(String title, int width, int height, String image) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(width, height);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(R.getInstance().getImage(image).getImage());
        return frame;
    }

    public static JPanel createJPanel(int width, int height, LayoutManager lm) {
        JPanel panel = new JPanel();
        panel.setLayout(lm);
        panel.setSize(width, height);
        return panel;
    }

    public static JButton createJButton(int posX, int posY, int width, int height, String image) {
        JButton button = new JButton();
        button.setLocation(posX, posY);
        button.setSize(width, height);
        button.setBackground(Color.white);
        button.setIcon(R.getInstance().getImage(image));
        return button;
    }

    public static JButton createJButton(int posX, int posY, int width, int height) {
        JButton button = new JButton();
        button.setLocation(posX, posY);
        button.setSize(width, height);
        button.setBackground(Color.white);
        return button;
    }

    public static JLabel createJLabel(int posX, int posY, int width, int height, String image) {
        JLabel label = new JLabel();
        label.setIcon(R.getInstance().getImage(image));
        label.setSize(width, height);
        label.setLocation(posX, posY);
        label.setBorder(null);
        label.setBackground(Color.black);
        return label;
    }
}
