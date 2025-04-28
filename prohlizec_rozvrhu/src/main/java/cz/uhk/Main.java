package cz.uhk;

import javax.swing.SwingUtilities;

import cz.uhk.gui.MainFraime;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFraime().setVisible(true));
    }
}