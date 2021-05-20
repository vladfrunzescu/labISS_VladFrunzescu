package persistence;

import domain.Angajat;

import java.util.List;

public interface IRepositoryAngajat {

    public Angajat findOne(Long id);

    public List<Angajat> filterByPresence();

    public Angajat login(String username, String parola);

    public Angajat update(Angajat angajat);
}
