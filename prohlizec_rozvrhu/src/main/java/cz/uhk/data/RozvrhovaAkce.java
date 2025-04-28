package cz.uhk.data;

import java.time.LocalTime;

public class RozvrhovaAkce {
    private String predmet;
    private String nazev;
    private String den;
    private String typAkce;
    private CasovyUdaj hodinaSkutOd;
    private CasovyUdaj hodinaSkutDo;
    private Ucitel ucitel;

    public LocalTime getCasOd() {
        String[] parts = hodinaSkutOd.getValue().split(":");
        return LocalTime.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }
    public LocalTime getCasDo() {
        String[] parts = hodinaSkutDo.getValue().split(":");
        return LocalTime.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    public CasovyUdaj getHodinaSkutOd() {
        return hodinaSkutOd;
    }
    public void setHodinaSkutOd(CasovyUdaj hodinaSkutOd) {
            this.hodinaSkutOd = hodinaSkutOd;
    }
    public CasovyUdaj getHodinaSkutDo() {
        return hodinaSkutDo;
    }
    public void setHodinaSkutDo(CasovyUdaj hodinaSkutDo) {
        this.hodinaSkutDo = hodinaSkutDo;
    }
    public String getTypAkce() {
        return typAkce;
    }
    public void setTypAkce(String typAkce) {
        this.typAkce = typAkce;
    }
    public String getPredmet() {
        return predmet;
    }
    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }
    public String getNazev() {
        return nazev;
    }
    public void setNazev(String nazev) {
        this.nazev = nazev;
    }
    public String getDen() {
        return den;
    }
    public void setDen(String den) {
        this.den = den;
    }
    public Ucitel getUcitel() {
        return ucitel;
    }
    public void setUcitel(Ucitel ucitel) {
        this.ucitel = ucitel;
    }

    public boolean prekryvaSeSAkci(RozvrhovaAkce akce) {
        if (this.getCasOd().isBefore(akce.getCasOd().plusMinutes(1))) {
            return this.getCasDo().isAfter(akce.getCasOd().minusMinutes(1));
        } else {
            return this.getCasOd().isAfter(akce.getCasDo().minusMinutes(1));
        }
    }
}
