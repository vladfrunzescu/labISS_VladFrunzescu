package controller;

import domain.Angajat;
import domain.Sarcina;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.AllServices;
import utils.IObservable;
import utils.IObserver;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AngajatController implements IObserver {
    public AllServices allService;
    public Angajat angajat;
    public Stage dialogStage;

    private final ObservableList<Sarcina> model_tableSarcini = FXCollections.observableArrayList();
    @FXML
    private TableView<Sarcina> tableView;
    @FXML
    private TableColumn<Sarcina, String> tableColumnDescription;
    @FXML
    private TableColumn<Sarcina, String> tableColumnStatus;
    @FXML
    private ComboBox<String> comboBoxStatus = new ComboBox<String>();
    @FXML
    private ComboBox<String> comboBoxHour = new ComboBox<String>();
    @FXML
    private ComboBox<String> comboBoxMinute = new ComboBox<String>();


    public void set(AllServices service, Angajat angajat, Stage dialogStage) {
        this.allService = service;
        this.angajat = angajat;
        this.dialogStage = dialogStage;
        init();
    }

    public void init(){
        model_tableSarcini.setAll(allService.getAllSarciniForAngajat(angajat.getId()));
    }

    @FXML
    public void initialize() {
        List<String> status_options = new ArrayList<>();
        status_options.add("NEINCEPUTA");
        status_options.add("IN_CURS");
        status_options.add("FINALIZATA");
        ObservableList<String> observableStatusList = FXCollections.observableArrayList(status_options);
        comboBoxStatus.setItems(observableStatusList);

        List<String> hour_options = new ArrayList<>();
        for (int hour = 0; hour <= 23; hour++) {
            hour_options.add(String.valueOf(hour));
        }
        ObservableList<String> observableHoursList = FXCollections.observableArrayList(hour_options);
        comboBoxHour.setItems(observableHoursList);

        List<String> minutes_options = new ArrayList<>();
        for (int minute = 0; minute <= 60; minute++) {
            minutes_options.add(String.valueOf(minute));
        }
        ObservableList<String> observableMinutesList = FXCollections.observableArrayList(minutes_options);
        comboBoxMinute.setItems(observableMinutesList);

        tableColumnDescription.setCellValueFactory(new PropertyValueFactory<Sarcina, String>("Descriere"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<Sarcina, String>("Status"));
        tableView.setItems(model_tableSarcini);
    }

    @FXML
    public void handleUpdateRequest() {
        String status = comboBoxStatus.getValue();
        Sarcina task = tableView.getSelectionModel().getSelectedItem();
        if (task != null && status != null && !status.equals("")) {
            task.setStatus(status);
            Sarcina result = allService.updateSarcina(task);
            if (result == null) {
                MessageAlert.showErrorMessage(null, "Couldn't update request! Exception occurred.");
            } else {
                init();
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Information", "Request updated successfully!");
            }
        } else {
            MessageAlert.showErrorMessage(null, "Select a task and choose another status from the option list!");
        }
    }

    @FXML
    public void handleSetHour(){
        String hour = comboBoxHour.getSelectionModel().getSelectedItem();
        String minute = comboBoxMinute.getSelectionModel().getSelectedItem();
        if (hour != null && !hour.equals("") && minute != null && !minute.equals("")) {
            angajat.setOraConectare(hour + ":" + minute);
            angajat.setOraDeconectare("null");
            Angajat result = allService.changeHoursForAngajat(angajat);
            if (result == null) {
                MessageAlert.showErrorMessage(null, "Couldn't update login time! Exception occurred.");
            }
            else {
                //initModel();
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Information", "Login time updated successfully!");
            }
        }
        else {
            MessageAlert.showErrorMessage(null, "Select the hour and minute from the option list!");
        }
    }

    @FXML
    public void handleLogout(){
        try {
            angajat.setOraDeconectare(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
            allService.changeHoursForAngajat(angajat);

            allService.logoutAngajat(angajat);
            this.goToLogin();
        }
        catch (Exception ex) {
            MessageAlert.showErrorMessage(null, "Couldn't logout: " + ex);
        }
    }

    @Override
    public void update(Sarcina sarcina) {
        init();
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
}
