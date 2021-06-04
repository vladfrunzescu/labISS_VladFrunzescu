package domain;

import java.util.Objects;

public class Sarcina {
    Long id;
    String descriere;
    //Status status;
    String status;
    Long idAngajat;

    public Sarcina(){}

    public Sarcina(Long id, String descriere, String status, Long idAngajat) {
        this.id = id;
        this.descriere = descriere;
        this.status = status;
        this.idAngajat = idAngajat;
    }


    public Sarcina(String descriere, String status, Long idAngajat) {
        this.id = getRandomId();
        this.descriere = descriere;
        this.status = status;
        this.idAngajat = idAngajat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(Long idAngajat) {
        this.idAngajat = idAngajat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sarcina sarcina = (Sarcina) o;
        return Objects.equals(id, sarcina.id) &&
                Objects.equals(descriere, sarcina.descriere) &&
                status == sarcina.status &&
                Objects.equals(idAngajat, sarcina.idAngajat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descriere, status, idAngajat);
    }

    public Long getRandomId(){
        long leftLimit = 1L;
        long rightLimit = 100000L;
        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

        return generatedLong;
    }
}
