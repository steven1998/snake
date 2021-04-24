package be.uantwerpen.fti.ei;

import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var ex = new Snake();
            ex.setVisible(true);
        });

    }
}

