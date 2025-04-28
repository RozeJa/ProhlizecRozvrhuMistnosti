package cz.uhk.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import cz.uhk.data.RozvrhovaAkce;

public class RozvrhFrame extends JFrame {
    
    private List<RozvrhovaAkce> rozvrhoveAkce;

    public RozvrhFrame(String budova, String mistnost, List<RozvrhovaAkce> rozvrhoveAkce) {
        super(String.format("Rozvrh %s:%s", budova, mistnost));

        this.rozvrhoveAkce = rozvrhoveAkce;

        initContent();

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void initContent() {
        Container panel = this.getContentPane();
        panel.setLayout(new GridLayout(8, 17));
        
        LocalTime time = LocalTime.of(7, 25);
        for (int i = 1; i < 17; i++) {
            JLabel hodina = new JHodina(String.format("%s.", i-1), time, time.plus(LocalTime.of(0, 45)));
            panel.add(panel, panel);
        }


        add(panel);
    }

}
