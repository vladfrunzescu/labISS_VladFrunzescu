package persistence;

import domain.Angajat;
import domain.Sef;

public interface IRepositorySef {

    public Sef login(String username, String parola);
}
