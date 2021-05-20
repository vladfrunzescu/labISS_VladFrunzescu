package controller;

import domain.Angajat;
import domain.Sarcina;
import domain.Sef;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import service.AllServices;

public class SefController {

    ObservableList<Angajat> angajatiObservableList = FXCollections.observableArrayList();
    ObservableList<Sarcina> sarciniObservableList = FXCollections.observableArrayList();

    public AllServices allService;
    public Sef sef;

    @FXML
    public TableView<Angajat> tableViewAngajat;
    @FXML
    public TableColumn<String, Angajat> tableColumnNume;
    @FXML
    public TableColumn<String, Angajat> tableColumnUsername;
    @FXML
    public TableColumn<String, Angajat> tableColumnParola;
    @FXML
    public TableColumn<String, Angajat> tableColumnLoginHour;

    @FXML
    public TableView<Sarcina> tableViewSarcina;
    @FXML
    public TableColumn<String, Sarcina> tableColumnDescription;
    @FXML
    public TableColumn<String, Sarcina> tableColumnStatus;

    @FXML
    public TextArea textAreaDescription;

    public void set(AllServices service, Sef sef) {
        this.allService = service;
        this.sef = sef;
        init();
    }

    private void init(){
        angajatiObservableList.setAll(allService.getAllAngajatiPrezenti());
    }

    @FXML
    private void initialize() {
        tableColumnNume.setCellValueFactory(new PropertyValueFactory<>("nume"));
        tableColumnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tableColumnParola.setCellValueFactory(new PropertyValueFactory<>("parola"));
        tableColumnLoginHour.setCellValueFactory(new PropertyValueFactory<>("oraConectare"));
        tableViewAngajat.setItems(angajatiObservableList);

        tableColumnDescription.setCellValueFactory(new PropertyValueFactory<>("descriere"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableViewSarcina.setItems(sarciniObservableList);
    }

    public void handleClickAngajat(){
        Angajat selected = tableViewAngajat.getSelectionModel().getSelectedItem();
        if(selected != null){
            sarciniObservableList.setAll(allService.getAllSarciniForAngajat(selected.getId()));
        }
        else{
            MessageAlert.showErrorMessage(null, "Select an employee!");
        }
    }

    public void handleSendTask(){}

    public void handleUpdateTask(){}

    public void handleLogout(){}
}
