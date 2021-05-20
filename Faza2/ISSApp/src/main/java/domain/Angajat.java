package domain;

import java.util.Objects;

public class Angajat {
    Long id;
    String nume;
    String username;
    String parola;
    String oraConectare;
    String oraDeconectare;

    public Angajat(){}

    public Angajat(Long id, String nume, String username, String parola, String oraConectare, String oraDeconectare) {
        this.id = id;
        this.nume = nume;
        this.username = username;
        this.parola = parola;
        this.oraConectare = oraConectare;
        this.oraDeconectare = oraDeconectare;
    }

    public Angajat(String nume, String username, String parola, String oraConectare, String oraDeconectare) {
        this.id = getRandomId();
        this.nume = nume;
        this.username = username;
        this.parola = parola;
        this.oraConectare = oraConectare;
        this.oraDeconectare = oraDeconectare;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getOraConectare() {
        return oraConectare;
    }

    public void setOraConectare(String oraConectare) {
        this.oraConectare = oraConectare;
    }

    public String getOraDeconectare() {
        return oraDeconectare;
    }

    public void setOraDeconectare(String oraDeconectare) {
        this.oraDeconectare = oraDeconectare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Angajat angajat = (Angajat) o;
        return Objects.equals(id, angajat.id) &&
                Objects.equals(nume, angajat.nume) &&
                Objects.equals(username, angajat.username) &&
                Objects.equals(parola, angajat.parola) &&
                Objects.equals(oraConectare, angajat.oraConectare) &&
                Objects.equals(oraDeconectare, angajat.oraDeconectare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, username, parola, oraConectare, oraDeconectare);
    }

    public Long getRandomId(){
        long leftLimit = 1L;
        long rightLimit = 100000L;
        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

        return generatedLong;
    }
}
