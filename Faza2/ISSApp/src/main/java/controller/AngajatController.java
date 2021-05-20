package controller;

import domain.Angajat;
import service.AllServices;

public class AngajatController {
    public AllServices allService;
    public Angajat angajat;

    public void set(AllServices service, Angajat angajat) {
        this.allService = service;
        this.angajat = angajat;
        //init();
    }

    public void handleUpdateRequest(){}

    public void handleSetHour(){}

    public void handleLogout(){}
}
