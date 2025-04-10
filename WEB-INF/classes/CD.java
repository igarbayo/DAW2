// Ruta: WEB-INF/classes/tienda/modelo/CD.java
package tienda.modelo;

public class CD {
    private String titulo;
    private String autor;
    private String pais;
    private double precio;

    public CD(String titulo, String autor, String pais, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.pais = pais;
        this.precio = precio;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getPais() { return pais; }
    public double getPrecio() { return precio; }

    public String toString() {
        return titulo + " | " + autor + " | " + pais + " | $" + precio;
    }

    public static CD fromString(String data) {
        String[] partes = data.split("\\|");
        return new CD(
            partes[0].trim(),
            partes[1].trim(),
            partes[2].trim(),
            Double.parseDouble(partes[3].replace("$", "").trim())
        );
    }
}
