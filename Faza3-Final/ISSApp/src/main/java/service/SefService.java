package service;

import domain.Angajat;
import domain.Sef;
import persistence.RepositoryAngajat;
import persistence.RepositorySef;

public class SefService {
    private final RepositorySef repositorySef;

    public SefService(RepositorySef repositorySef) {
        this.repositorySef = repositorySef;
    }

    public Sef login(String username, String parola) {
        Sef res = repositorySef.login(username, parola);
        return res;
    }
}
