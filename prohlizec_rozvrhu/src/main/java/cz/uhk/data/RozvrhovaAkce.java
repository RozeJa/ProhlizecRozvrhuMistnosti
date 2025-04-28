package cz.uhk.data;

public class RozvrhovaAkce {
    private String predmet;
    private String nazev;
    private String den;
    private String typAkce;
    private CasovyUdaj hodinaSkutOd;
    private CasovyUdaj hodinaSkutDo;
    private Ucitel ucitel;

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
}
