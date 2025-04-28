package cz.uhk.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import cz.uhk.data.RozvrhovaAkce;

public class TabulkaAkci extends AbstractTableModel {
    private List<RozvrhovaAkce> akce = new ArrayList<>();
    private String[] nazvySloupcu = new String[] {
        "Zkratka",
        "Nazev Predmetu",
        "Den v týdnu",
        "Začátek",
        "Konec",
        "Učitel"
    };

    @Override
    public String getColumnName(int column) {
        return nazvySloupcu[column];
    }

    public List<RozvrhovaAkce> getAkce() {
        return akce;
    }

    public void setAkce(List<RozvrhovaAkce> akce) {
        this.akce = akce;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {
        return akce.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RozvrhovaAkce vybranaAkce = akce.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return vybranaAkce.getPredmet();
            case 1:
                return vybranaAkce.getNazev();
            case 2:
                return vybranaAkce.getDen();
            case 3:
                return vybranaAkce.getHodinaSkutOd().getValue();
            case 4:
                return vybranaAkce.getHodinaSkutDo().getValue();
            case 5:
                if (vybranaAkce.getUcitel() != null)  
                    return String.format("%s %s", vybranaAkce.getUcitel().getJmeno(), vybranaAkce.getUcitel().getPrijmeni());
                return "";
            default:
                return null;
        }
    }    
}
