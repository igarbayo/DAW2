package Model.Utilities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

/* JavaBean Cart */

public class Cart implements Serializable {
    private Map<CD, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }

    public Map<CD, Integer> getItems() {
        return items;
    }

    public void setItems(Map<CD, Integer> items) {
        this.items = items;
    }

    public void addItem(CD cd, int cantidad) {
        if (cd == null || cantidad <= 0) return;
        items.put(cd, cantidad);
    }

    public void removeUnit(CD cd) {
        if (cd == null || !items.containsKey(cd)) return;

        int current = items.get(cd);
        if (current > 1) {
            items.put(cd, current - 1);
        } else {
            items.remove(cd);
        }
    }

    public void deleteItem(CD cd) {
        items.remove(cd);
    }

    public void deleteItemByTitle(String titulo) {
        Iterator<Map.Entry<CD, Integer>> iter = items.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<CD, Integer> entry = iter.next();
            if (entry.getKey().getTitle().equals(titulo)) {
                iter.remove();
                break; // elimina solo el primero que coincida
            }
        }
    }

    public double calcTotalPrice() {
        double total = 0;
        for (Map.Entry<CD, Integer> entry : items.entrySet()) {
            CD cd = entry.getKey();
            int cantidad = entry.getValue();
            total += cd.getPrice() * cantidad;
        }
        return total;
    }

    public void emptyItems() {
        items.clear();
    }
}
