package service;

import domain.Sarcina;
import persistence.RepositorySarcina;

import java.util.List;

public class SarcinaService {

    private final RepositorySarcina sarcinaRepository;

    public SarcinaService(RepositorySarcina sarcinaRepository) {
        this.sarcinaRepository = sarcinaRepository;
    }

    public List<Sarcina> filterByAngajat(Long idAngajat){
        return sarcinaRepository.filterByAngajat(idAngajat);
    }
}
