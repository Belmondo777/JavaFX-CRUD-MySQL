package sample.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class MuzZena {
    private SimpleIntegerProperty muz_id;
    private SimpleStringProperty muz_ime;
    private SimpleStringProperty muz_prezime;
    private SimpleLongProperty muz_godiste;
    private SimpleIntegerProperty muz_zena;
    private SimpleIntegerProperty zena_id;
    private SimpleStringProperty zena_ime;
    private SimpleStringProperty zena_prezime;
    private SimpleLongProperty zena_godiste;

    public MuzZena() {
    }

    public MuzZena(int muz_id, String muz_ime, String muz_prezime, long muz_godiste, int muz_zena, int zena_id, String zena_ime, String zena_prezime, long zena_godiste) {
        this.muz_id = new SimpleIntegerProperty(muz_id);
        this.muz_ime = new SimpleStringProperty(muz_ime);
        this.muz_prezime = new SimpleStringProperty(muz_prezime);
        this.muz_godiste = new SimpleLongProperty(muz_godiste);
        this.muz_zena = new SimpleIntegerProperty(muz_zena);
        this.zena_id = new SimpleIntegerProperty(zena_id);
        this.zena_ime = new SimpleStringProperty(zena_ime);
        this.zena_prezime = new SimpleStringProperty(zena_prezime);
        this.zena_godiste = new SimpleLongProperty(zena_godiste);
    }

    public int getMuz_id() {
        return muz_id.get();
    }

    public SimpleIntegerProperty muz_idProperty() {
        return muz_id;
    }

    public void setMuz_id(int muz_id) {
        this.muz_id.set(muz_id);
    }

    public String getMuz_ime() {
        return muz_ime.get();
    }

    public SimpleStringProperty muz_imeProperty() {
        return muz_ime;
    }

    public void setMuz_ime(String muz_ime) {
        this.muz_ime.set(muz_ime);
    }

    public String getMuz_prezime() {
        return muz_prezime.get();
    }

    public SimpleStringProperty muz_prezimeProperty() {
        return muz_prezime;
    }

    public void setMuz_prezime(String muz_prezime) {
        this.muz_prezime.set(muz_prezime);
    }

    public long getMuz_godiste() {
        return muz_godiste.get();
    }

    public SimpleLongProperty muz_godisteProperty() {
        return muz_godiste;
    }

    public void setMuz_godiste(long muz_godiste) {
        this.muz_godiste.set(muz_godiste);
    }

    public int getMuz_zena() {
        return muz_zena.get();
    }

    public SimpleIntegerProperty muz_zenaProperty() {
        return muz_zena;
    }

    public void setMuz_zena(int muz_zena) {
        this.muz_zena.set(muz_zena);
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

    public String getZena_ime() {
        return zena_ime.get();
    }

    public SimpleStringProperty zena_imeProperty() {
        return zena_ime;
    }

    public void setZena_ime(String zena_ime) {
        this.zena_ime.set(zena_ime);
    }

    public String getZena_prezime() {
        return zena_prezime.get();
    }

    public SimpleStringProperty zena_prezimeProperty() {
        return zena_prezime;
    }

    public void setZena_prezime(String zena_prezime) {
        this.zena_prezime.set(zena_prezime);
    }

    public long getZena_godiste() {
        return zena_godiste.get();
    }

    public SimpleLongProperty zena_godisteProperty() {
        return zena_godiste;
    }

    public void setZena_godiste(long zena_godiste) {
        this.zena_godiste.set(zena_godiste);
    }
}
