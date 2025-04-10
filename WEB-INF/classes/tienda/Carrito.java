
package classes.tienda;

import java.util.*;

public class Carrito {
    private List<Item> items = new ArrayList<>();

    public void agregar(CD cd, int cantidad) {
        items.add(new Item(cd, cantidad));
    }

    public void eliminar(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public double getTotal() {
        double total = 0;
        for (Item i : items) {
            total += i.getCD().getPrecio() * i.getCantidad();
        }
        return total;
    }

    public static class Item {
        private CD cd;
        private int cantidad;

        public Item(CD cd, int cantidad) {
            this.cd = cd;
            this.cantidad = cantidad;
        }

        public CD getCD() { return cd; }
        public int getCantidad() { return cantidad; }
    }
}
