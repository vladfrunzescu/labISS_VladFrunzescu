package domain;

import java.util.Objects;

public class Sef {
    Long id;
    String nume;
    String username;
    String parola;

    public Sef(){}

    public Sef(Long id, String nume, String username, String parola) {
        this.id = id;
        this.nume = nume;
        this.username = username;
        this.parola = parola;
    }

    public Sef(String nume, String username, String parola) {
        this.id = getRandomId();
        this.nume = nume;
        this.username = username;
        this.parola = parola;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sef sef = (Sef) o;
        return Objects.equals(id, sef.id) &&
                Objects.equals(nume, sef.nume) &&
                Objects.equals(username, sef.username) &&
                Objects.equals(parola, sef.parola);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, username, parola);
    }

    public Long getRandomId(){
        long leftLimit = 1L;
        long rightLimit = 100000L;
        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

        return generatedLong;
    }

}
