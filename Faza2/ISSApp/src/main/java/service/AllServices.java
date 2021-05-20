package service;

import domain.Angajat;
import domain.Sarcina;
import domain.Sef;

import java.util.List;

public class AllServices {
    private final AngajatService angajatService;
    private final SarcinaService sarcinaService;
    private final SefService sefService;

    public AllServices(AngajatService angajatService, SarcinaService sarcinaService, SefService sefService) {
        this.angajatService = angajatService;
        this.sarcinaService = sarcinaService;
        this.sefService = sefService;
    }

    public Angajat loginAngajat(String username, String parola){
        return angajatService.login(username, parola);
    }

    public Sef loginSef(String username, String parola){
        return sefService.login(username, parola);
    }

    public List<Angajat> getAllAngajatiPrezenti(){
        return angajatService.filterByPresence();
    }

    public List<Sarcina> getAllSarciniForAngajat(Long idAngajat){
        return sarcinaService.filterByAngajat(idAngajat);
    }


}
