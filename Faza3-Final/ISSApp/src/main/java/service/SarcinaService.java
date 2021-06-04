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

    public Sarcina saveSarcina(Sarcina sarcina) {
        return this.sarcinaRepository.save(sarcina);
    }

    public Sarcina updateSarcina(Sarcina sarcina) {
        return this.sarcinaRepository.update(sarcina);
    }

    public Sarcina deleteSarcina(Sarcina sarcina) {
        return this.sarcinaRepository.delete(sarcina);
    }
}
