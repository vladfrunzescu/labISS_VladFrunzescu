package controller;

import domain.Angajat;
import domain.Sef;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.AllServices;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField textFieldUsername;
    @FXML
    public TextField textFieldParola;
    @FXML
    public CheckBox checkBoxManager = new CheckBox();

    private AllServices allService;
    private Stage stage;

    public void set(AllServices allService, Stage stage) {
        this.allService = allService;
        this.stage = stage;
    }

    public void handleLogin() {
        String username = textFieldUsername.getText();
        String password = textFieldParola.getText();

        if (username.equals("") || password.equals("")) {
            MessageAlert.showErrorMessage(null, "Enter username and password!");
            return;
        }

        if (checkBoxManager.isSelected()) {
            Sef sef = allService.loginSef(textFieldUsername.getText(), textFieldParola.getText());
            if (sef != null) {
                showMainWindowSef(sef);
                hide();
            }
        } else {
            Angajat angajat = allService.loginAngajat(textFieldUsername.getText(), textFieldParola.getText());
            if (angajat != null) {
                showMainWindowAngajat(angajat);
                hide();
            } else {
                MessageAlert.showErrorMessage(null, "Wrong user!");
            }
        }
    }

        private void hide () {
            stage.hide();
        }

        private void showMainWindowSef (Sef sef){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/managerView.fxml"));

                AnchorPane root = loader.load();

                Stage sefStage = new Stage();
                sefStage.setTitle("Manager:" + sef.getNume());
                sefStage.initModality(Modality.WINDOW_MODAL);

                Scene scene = new Scene(root);
                sefStage.setScene(scene);

                SefController sefController = loader.getController();
                sefController.set(allService, sef);

                sefStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    private void showMainWindowAngajat(Angajat angajat) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/employeeView.fxml"));

            AnchorPane root = loader.load();

            Stage angajatStage = new Stage();
            angajatStage.setTitle("Angajat:" + angajat.getNume());
            angajatStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(root);
            angajatStage.setScene(scene);

            AngajatController angajatController = loader.getController();
            angajatController.set(allService, angajat);

            angajatStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
