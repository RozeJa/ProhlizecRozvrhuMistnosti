package cz.uhk.gui;

import java.awt.BorderLayout;
import java.time.LocalTime;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JHodina extends JPanel {

    private String poradiHodiny;
    private LocalTime zacatek;
    private LocalTime konec;

    public String getPoradiHodiny() {
        return poradiHodiny;
    }

    public void setPoradiHodiny(String poradiHodiny) {
        this.poradiHodiny = poradiHodiny;
    }

    public LocalTime getZacatek() {
        return zacatek;
    }

    public void setZacatek(LocalTime zacatek) {
        this.zacatek = zacatek;
    }

    public LocalTime getKonec() {
        return konec;
    }

    public void setKonec(LocalTime konec) {
        this.konec = konec;
    }

    public JHodina(String poradiHodiny, LocalTime zacatek, LocalTime konec) {
        this.poradiHodiny = poradiHodiny;
        this.zacatek = zacatek;
        this.konec = konec;        
        
        add(new JLabel(poradiHodiny));
        add(new JLabel(zacatek.toString()), BorderLayout.NORTH);
        add(new JLabel(konec.toString()), BorderLayout.SOUTH);
    }

    
}
