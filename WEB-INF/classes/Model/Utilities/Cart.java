package Model.Utilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JavaBean Cart */

public class Cart implements Serializable {
    private List<CD> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<CD> getItems() {
        return items;
    }

    public void setItems(List<CD> items) {
        this.items = items;
    }

    public void addItem(CD cd, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            items.add(cd);
        }
    }

    public void deleteItem(CD cd) {
        items.remove(cd);
    }

    public void deleteItemByTitle(String titulo) {

        /* Iterates over the cart's items */
        Iterator<CD> iter = items.iterator();
        while (iter.hasNext()) {
            CD item = iter.next();
            /* Compares the title to the given one */
            if (item.getTitle().equals(titulo)) {
                /* Removes the item from the cart */
                iter.remove();
                break; // deletes only the first match
            }
        }
    }

    public double calcTotalPrice() {

        double total = 0;
        for (CD cd : items) {
            total += cd.getPrice();
        }
        return total;
    }

    public void emptyItems() {
        items.clear();
    }
}

