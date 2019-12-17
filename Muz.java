package sample.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Muz {
    private SimpleIntegerProperty id;
    private SimpleStringProperty ime;
    private SimpleStringProperty prezime;
    private SimpleLongProperty godiste;
    private SimpleIntegerProperty zena_id;

    public Muz() {
    }

    public Muz(int id, String ime, String prezime, long godiste, int zena_id) {
        this.id = new SimpleIntegerProperty(id);
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
        this.godiste = new SimpleLongProperty(godiste);
        this.zena_id = new SimpleIntegerProperty(zena_id);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getIme() {
        return ime.get();
    }

    public SimpleStringProperty imeProperty() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime.set(ime);
    }

    public String getPrezime() {
        return prezime.get();
    }

    public SimpleStringProperty prezimeProperty() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime.set(prezime);
    }

    public long getGodiste() {
        return godiste.get();
    }

    public SimpleLongProperty godisteProperty() {
        return godiste;
    }

    public void setGodiste(long godiste) {
        this.godiste.set(godiste);
    }

    public int getZena_id() {
        return zena_id.get();
    }

    public SimpleIntegerProperty zena_idProperty() {
        return zena_id;
    }

    public void setZena_id(int zena_id) {
        this.zena_id.set(zena_id);
    }
}
