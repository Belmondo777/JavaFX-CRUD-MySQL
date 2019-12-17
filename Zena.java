package sample.models;

public class Zena {
    private int id;
    private String ime;
    private String prezime;
    private long godiste;

    public Zena() {
    }

    public Zena(int id, String ime, String prezime, long godiste) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.godiste = godiste;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public long getGodiste() {
        return godiste;
    }

    public void setGodiste(long godiste) {
        this.godiste = godiste;
    }

    @Override
    public String toString() {
        String str = this.id + " - " + this.ime + " " + this.prezime + " (" + this.godiste + ")";
        return str;
    }
}
