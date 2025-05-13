package Model.Utilities;

import java.io.Serializable;
import java.util.Objects;

public class CD implements Serializable {
    
    private String title;
    private String artist;
    private String country;
    private double price;


    public CD(String title, String artist, String country, double price) {
        this.title = title;
        this.artist = artist;
        this.country = country;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CD)) return false;
        CD cd = (CD) o;
        return Objects.equals(title, cd.title) &&
               Objects.equals(artist, cd.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist);
    }
}
