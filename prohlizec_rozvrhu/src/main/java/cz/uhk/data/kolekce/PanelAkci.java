package cz.uhk.data.kolekce;

import java.awt.GridBagConstraints;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JPanel;

import cz.uhk.data.RozvrhovaAkce;
import cz.uhk.gui.JHodina;
import cz.uhk.gui.JVyucHodina;

public class PanelAkci {
    List<List<RozvrhovaAkce>> akceNaSkupiny = new ArrayList<>();
    
    public List<List<RozvrhovaAkce>> getAkceNaSkupiny() {
        return akceNaSkupiny;
    }

    public PanelAkci(List<RozvrhovaAkce> akce) {
        akceNaSkupiny.add(new ArrayList<>());
        rozdelAkceDoSkupin(akce);
    }

    private void rozdelAkceDoSkupin(List<RozvrhovaAkce> akce) {
        for (RozvrhovaAkce rozvrhovaAkce : akce) {

            boolean pridanoDoSkupiny = false;
            for (List<RozvrhovaAkce> skupina : akceNaSkupiny) {
                Optional<RozvrhovaAkce> shoda = skupina.stream()
                    .filter(a -> a.prekryvaSeSAkci(rozvrhovaAkce))
                    .findFirst();
                
                if (shoda.isEmpty()) {
                    skupina.add(rozvrhovaAkce);
                    pridanoDoSkupiny = true;
                    break;
                }
            }

            if (!pridanoDoSkupiny) {
                List<RozvrhovaAkce> novaSkupina = new ArrayList<>();
                novaSkupina.add(rozvrhovaAkce);

                akceNaSkupiny.add(novaSkupina);
            }
        }
    }

    public void vykrelsiAkce(JPanel panel, GridBagConstraints g, JHodina[] vyucHodiny) {
        int gridxDefault = g.gridx;

        for (List<RozvrhovaAkce> skupinaAkci : akceNaSkupiny) {

            for (JHodina jHodina : vyucHodiny) {
                LocalTime zacatek = jHodina.getZacatek();
                Optional<RozvrhovaAkce> zaznam = skupinaAkci.stream().filter(a -> a.getCasOd().equals(zacatek)).findFirst();

                if (zaznam.isPresent()) {
                    RozvrhovaAkce akceKVyhlesleniNaUI = zaznam.get();
                    if (akceKVyhlesleniNaUI.getCasDo().equals(jHodina.getKonec())) {
                        panel.add(new JVyucHodina(akceKVyhlesleniNaUI), g);
                    } else {
                        g.gridwidth = 2;
                        panel.add(new JVyucHodina(akceKVyhlesleniNaUI), g);

                        g.gridwidth = 1;
                    }
                } else {
                    panel.add(new JPanel(), g);
                }

                g.gridx++;
            }

            g.gridy++;
            g.gridx = gridxDefault;
        }
    }
}
