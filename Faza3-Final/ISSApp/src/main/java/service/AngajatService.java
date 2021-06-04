package service;

import domain.Angajat;
import persistence.RepositoryAngajat;
import persistence.RepositorySarcina;

import java.util.List;

public class AngajatService {
    private final RepositoryAngajat repositoryAngajat;

    public AngajatService(RepositoryAngajat repositoryAngajat) {
        this.repositoryAngajat = repositoryAngajat;
    }

    public Angajat login(String username, String parola) {
        Angajat res = repositoryAngajat.login(username, parola);

        return res;
    }

    public List<Angajat> filterByPresence(){
        return repositoryAngajat.filterByPresence();
    }

    public Angajat update(Angajat angajat){
        return repositoryAngajat.update(angajat);
    }
}
