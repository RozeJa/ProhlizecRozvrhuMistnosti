package cz.uhk.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

import cz.uhk.data.RozvrhovaAkce;

public class JVyucHodina extends JPanel {

    private static Random r = new Random();
    
    public JVyucHodina(RozvrhovaAkce rozvrhovaAkce) {

        setLayout(new GridLayout(3, 1));

        add(new JLabel(rozvrhovaAkce.getPredmet()));
        add(new JLabel(rozvrhovaAkce.getNazev()));
        add(new JLabel(rozvrhovaAkce.getUcitel().getPrijmeni()));

        setBackground(new Color(randomNumber(), randomNumber(), randomNumber()));
    }

    private int randomNumber() {
        return r.nextInt(128) + 127;
    }
}
