package cz.uhk.data.kolekce;

import java.util.List;

import cz.uhk.data.RozvrhovaAkce;

public class RozvrhoveAkce {
    private List<RozvrhovaAkce> rozvrhovaAkce;

    public List<RozvrhovaAkce> getRozvrhovaAkce() {
        return rozvrhovaAkce.stream().filter((ra) -> ra.getTypAkce().equals("Přednáška") || ra.getTypAkce().equals("Cvičení")).toList();
    }

    public void setRozvrhovaAkce(List<RozvrhovaAkce> rozvrhovaAkce) {
        this.rozvrhovaAkce = rozvrhovaAkce;
    }   
}
