package persistence;

import domain.Sarcina;

import java.util.List;

public interface IRepositorySarcina {

    public Sarcina save(Sarcina sarcina);

    public Sarcina update(Sarcina sarcina);

    public Sarcina delete(Sarcina sarcina);

    public List<Sarcina> filterByAngajat(Long idAngajat);
}
