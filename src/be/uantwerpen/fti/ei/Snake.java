package be.uantwerpen.fti.ei;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class Snake extends JFrame {

    private JLabel statusbar;

    public Snake() {
        initUI();
    }

    public JLabel getStatusBar() {

        return statusbar;
    }

    private void initUI() {
        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);
        add(new SnakeScreen(this));
        setTitle("Mini Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(315, 350);
        setResizable(false);
        setLocationRelativeTo(null);
    }

}

