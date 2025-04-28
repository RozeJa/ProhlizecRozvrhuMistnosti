package cz.uhk.data.kolekce;

import java.util.List;

import cz.uhk.data.Budova;

public class Budovy {
    
    private List<Budova> items;

    public List<Budova> getItems() {
        return items;
    }

    public void setItems(List<Budova> items) {
        this.items = items;
    }

    public List<String> prevedBudovyNaStringy() {
        return items.stream()
            .filter(b -> b.getZkrBudovy().matches("^[A-Z]$"))
            .map(b -> b.getZkrBudovy())
            .sorted((a,b) -> a.compareTo(b))
            .toList();
    }
}
