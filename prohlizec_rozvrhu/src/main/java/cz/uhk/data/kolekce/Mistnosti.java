package cz.uhk.data.kolekce;

import java.util.List;

import cz.uhk.data.Mistnost;

public class Mistnosti {
    
    private List<Mistnost> mistnostInfo;

    public List<Mistnost> getMistnostInfo() {
        return mistnostInfo;
    }

    public void setMistnostInfo(List<Mistnost> mistnostInfo) {
        this.mistnostInfo = mistnostInfo;
    }

    public List<String> prevedMistnostiNaStringy() {
        return mistnostInfo.stream()
            .filter(b -> b.getCisloMistnosti().matches("^[A-Z][0-9]+$"))
            .map(b -> b.getCisloMistnosti())
            .sorted((a,b) -> a.compareTo(b))
            .toList();
    }
}
