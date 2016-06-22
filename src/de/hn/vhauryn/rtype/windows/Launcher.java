package de.hn.vhauryn.rtype.windows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import de.hn.vhauryn.rtype.ui.Helper;
import de.hn.vhauryn.rtype.ui.R;

public class Launcher extends JFrame {
    private static final long serialVersionUID = 1L;

    public static void main(String args[]) {
        new Launcher();
    }


    protected Image icon = null;
    protected JPanel panel = null;
    protected JLabel label = null;
    protected JButton button_start = null;
    protected JButton button_exit = null;

    public Launcher() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(480, 320);
        setLocationRelativeTo(null);
        setIconImage(R.getInstance().getImage("r-type-icon.png").getImage());

        addControls();

        bindActionListener();

        setVisible(true);
    }

    private void addControls(){

        panel = Helper.createJPanel(getWidth(), getHeight(), null);
        label = Helper.createJLabel(0, 0,getWidth(), getHeight(), "setup_bg.png");

        panel.add(label);

        button_start = Helper.createJButton(350, 230, 55, 55, "play.png");
        button_exit = Helper.createJButton(410, 230, 55, 55, "exit.png");

        panel.setComponentZOrder(button_start, 0);
        panel.setComponentZOrder(button_exit, 0);

        add(panel);
    }

    private void bindActionListener() {

        button_start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    button_start.setVisible(false);
                    button_exit.setVisible(false);
                    label.setVisible(false);
                    label = Helper.createJLabel(0, 0, getWidth(),getHeight(), "ready_go.gif");
                    panel.add(label);
                    setOpacity(1);
                    label.setVisible(true);

                    Timer t = new Timer(3000, actionEvent -> {
                        setVisible(false);
                        button_start.setVisible(true);
                        button_exit.setVisible(true);
                        new GameWindow();
                        dispose();
                    });

                    t.setRepeats(false);
                    t.start();
                } catch (Exception f) {
                    System.err.print(f.getStackTrace());
                }
            }
        });

        button_exit.addActionListener(actionEvent ->
                System.exit(0));
    }

}