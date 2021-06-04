package controller;

import domain.Angajat;
import domain.Sarcina;
import domain.Sef;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.AllServices;
import utils.IObserver;

import java.io.IOException;

public class SefController implements IObserver {

    ObservableList<Angajat> angajatiObservableList = FXCollections.observableArrayList();
    ObservableList<Sarcina> sarciniObservableList = FXCollections.observableArrayList();

    public AllServices allService;
    public Sef sef;
    public Stage dialogStage;

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

    public void set(AllServices service, Sef sef, Stage dialogStage) {
        this.allService = service;
        this.sef = sef;
        this.dialogStage = dialogStage;
        init();
    }

    @Override
    public void update(Sarcina sarcina) {
        if(sarcina != null){
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Sarcina finalizata!", "Angajatul cu id-ul " + sarcina.getIdAngajat().toString() + " a terminat sarcina identificata prin numarul " + sarcina.getId().toString() + " cu descrierea " + sarcina.getDescriere());
        }
        init();
    }

    private void init(){
        angajatiObservableList.setAll(allService.getAllAngajatiPrezenti());
        Angajat selected = tableViewAngajat.getSelectionModel().getSelectedItem();
        if (selected != null) {
            sarciniObservableList.setAll(allService.getAllSarciniForAngajat(selected.getId()));
        }
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

    @FXML
    public void handleClickAngajat(){
        Angajat selected = tableViewAngajat.getSelectionModel().getSelectedItem();
        if(selected != null){
            sarciniObservableList.setAll(allService.getAllSarciniForAngajat(selected.getId()));
        }
        else{
            MessageAlert.showErrorMessage(null, "Select an employee!");
        }
    }

    @FXML
    public void handleSendTask() {
        String description = textAreaDescription.getText();
        Angajat employee = tableViewAngajat.getSelectionModel().getSelectedItem();
        if (employee != null && description != null && !description.equals("")) {
            Sarcina result = allService.sendSarcina(new Sarcina(description, "NEINCEPUTA", employee.getId()));
            if (result == null) {
                MessageAlert.showErrorMessage(null, "Couldn't send request! Exception occurred.");
            } else {
                sarciniObservableList.setAll(allService.getAllSarciniForAngajat(employee.getId()));
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Information", "Request sent successfully!");
            }
        } else {
            MessageAlert.showErrorMessage(null, "Select an employee and complete the description field!");
        }
    }

    @FXML
    public void handleUpdateTask(){
        String descriere = textAreaDescription.getText();
        Sarcina task = tableViewSarcina.getSelectionModel().getSelectedItem();
        if (task != null && descriere != null && !descriere.equals("")) {
            task.setDescriere(descriere);
            Sarcina result = allService.updateSarcina(task);
            textAreaDescription.setText("");
            if (result == null) {
                MessageAlert.showErrorMessage(null, "Couldn't update request! Exception occurred.");
            } else {
                init();
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Information", "Request updated successfully!");
            }
        } else {
            MessageAlert.showErrorMessage(null, "Select a task and enter the description!");
        }
    }

    @FXML
    public void handleLogout(){
        try {
            allService.logoutSef(sef);
            this.goToLogin();
        }
        catch (Exception ex) {
            MessageAlert.showErrorMessage(null, "Couldn't logout: " + ex);
        }
    }

    public void goToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/loginView.fxml"));

            AnchorPane root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Login Page");
            loginStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(root);
            loginStage.setScene(scene);

            LoginController loginController = loader.getController();
            loginController.set(allService, loginStage);

            dialogStage.close();
            loginStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handlePressTask(){
        Sarcina task = tableViewSarcina.getSelectionModel().getSelectedItem();
        if (task != null){
            textAreaDescription.setText(task.getDescriere());
        }
    }
}
