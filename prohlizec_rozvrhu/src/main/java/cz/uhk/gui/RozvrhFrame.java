package cz.uhk.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cz.uhk.data.RozvrhovaAkce;
import cz.uhk.data.kolekce.PanelAkci;

public class RozvrhFrame extends JFrame {
    
    private List<RozvrhovaAkce> rozvrhoveAkce;
    private String[] days = new String[] {
            "Pondělí",
            "Úterý",
            "Středa",
            "Čtvrtek",
            "Pátek",
            "Sobota",
            "Neděle"
        };


    public RozvrhFrame(String budova, String mistnost, List<RozvrhovaAkce> rozvrhoveAkce) {
        super(String.format("Rozvrh %s:%s", budova, mistnost));

        this.rozvrhoveAkce = rozvrhoveAkce;

        initContent();

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void initContent() {
        // layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.anchor = GridBagConstraints.LINE_START;
        g.fill = GridBagConstraints.BOTH;
        g.ipadx = 10;
        
        // hodiny
        JHodina[] vyucHodiny = new JHodina[16];
        LocalTime time = LocalTime.of(7, 25);
        g.gridx = 1;
        g.gridy = 0;

        for (int i = 0; i < 16; i++) {
            JHodina hodina = new JHodina(String.format("%s.", i), time, time.plusMinutes(45));
            vyucHodiny[i] = hodina;
            panel.add(hodina, g);
            
            time = time.plusMinutes(50);
            g.gridx++;
        }
        g.gridx = 1;
        g.gridy++;
        
        Map<String, PanelAkci> akce = new TreeMap<>();
        for (int i = 1; i < 8; i++) {
            String den = days[i-1];
            PanelAkci panelAkci = new PanelAkci(
                rozvrhoveAkce.stream()
                .filter(a -> a.getDen().equals(den))
                .toList());
            akce.put(den, panelAkci);
            panelAkci.vykrelsiAkce(panel, g, vyucHodiny);
        }

        g.gridx = 0;
        g.gridy = 1;
        for (int i = 0; i < days.length; i++) {

            PanelAkci panelAkci = akce.get(days[i]);
            int pocetSkupinVPaneluAkci = panelAkci.getAkceNaSkupiny().size();
            if (pocetSkupinVPaneluAkci > 0) {
                g.gridheight = pocetSkupinVPaneluAkci;
            }

            JPanel dayPanel = new JPanel();
            JLabel dayLabel = new JLabel(days[i]);
            Color c = null;
            if (i % 2 == 0) {
                c = new Color(200, 255, 255);
            } else {
                c = new Color(255, 255, 200);
            } 

            dayPanel.setBackground(c);
            dayPanel.add(dayLabel);
            panel.add(dayPanel, g);

            g.gridy += g.gridheight;
            g.gridheight = 1;
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);
    }
}
