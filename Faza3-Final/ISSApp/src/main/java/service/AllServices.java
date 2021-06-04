package service;

import domain.Angajat;
import domain.Sarcina;
import domain.Sef;
import utils.IObserver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AllServices {
    private final AngajatService angajatService;
    private final SarcinaService sarcinaService;
    private final SefService sefService;

    private final Map<Long, IObserver> loggedAngajati;
    private final Map<Long, IObserver> loggedSefi;

    public AllServices(AngajatService angajatService, SarcinaService sarcinaService, SefService sefService) {
        this.angajatService = angajatService;
        this.sarcinaService = sarcinaService;
        this.sefService = sefService;
        loggedAngajati = new ConcurrentHashMap<>();
        loggedSefi = new ConcurrentHashMap<>();
    }

    public void setObserverAngajat(Long id, IObserver client) {
        loggedAngajati.put(id, client);
    }

    public void setObserverSef(Long id, IObserver client) {
        loggedSefi.put(id, client);
    }

    public Angajat loginAngajat(String username, String parola) throws ServiceException {
        Angajat res = angajatService.login(username, parola);
        if (res != null) {
            if (loggedAngajati.get(res.getId()) != null) {
                throw new ServiceException("User already logged in!");
            }
        } else {
            throw new ServiceException("Authentification failed!");
        }
        return res;
    }

    public Sef loginSef(String username, String parola) throws ServiceException {
        Sef res = sefService.login(username, parola);
        if (res != null) {
            if (loggedSefi.get(res.getId()) != null) {
                throw new ServiceException("User already logged in!");
            }
        } else {
            throw new ServiceException("Authentification failed!");
        }
        return res;
    }

    public List<Angajat> getAllAngajatiPrezenti(){
        return angajatService.filterByPresence();
    }

    public List<Sarcina> getAllSarciniForAngajat(Long idAngajat){
        return sarcinaService.filterByAngajat(idAngajat);
    }

    public Sarcina sendSarcina(Sarcina sarcina) {
        Sarcina result = sarcinaService.saveSarcina(sarcina);
        notifyClients(null);
        return result;
    }

    public Sarcina updateSarcina(Sarcina sarcina) {
        Sarcina result = sarcinaService.updateSarcina(sarcina);
        if(sarcina.getStatus().equals("FINALIZATA")) {
            notifyClients(sarcina);
        }else{
            notifyClients(null);
        }
        return result;
    }

    public Sarcina deleteSarcina(Sarcina sarcina) {
        return sarcinaService.deleteSarcina(sarcina);
    }

    public Angajat changeHoursForAngajat(Angajat angajat){
        Angajat result = angajatService.update(angajat);
        notifyClients(null);
        return result;
    }

    public void logoutAngajat(Angajat angajat){
        IObserver localClients = loggedAngajati.remove(angajat.getId());
        if (localClients == null) {
            throw new ServiceException("User " + angajat.getNume() + " is not logged in!");
        }
    }

    public void logoutSef(Sef sef) {
        IObserver localClients = loggedSefi.remove(sef.getId());
        if (localClients == null) {
            throw new ServiceException("User " + sef.getNume() + " is not logged in!");
        }
    }

    public void notifyClients(Sarcina sarcina) {
        for (IObserver angajat : loggedAngajati.values()) {
            angajat.update(null);
        }
        for (IObserver sef : loggedSefi.values()) {
            sef.update(sarcina);
        }
    }
}
