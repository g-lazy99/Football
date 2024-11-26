package com.example.football;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TeamsEditDialogController {

    @FXML
    private TextField nameTF,popTF,popTA,popTU;
    private Teams teams;
    private boolean okClicked = false;
    private Stage dialogStage;


    @FXML
    private void initialize() {
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setPerson(Teams teams) {
        this.teams = teams;
        nameTF.setText(teams.getName());
        popTF.setText(teams.getTrainer());
        popTA.setText(teams.getCountry());
        popTU.setText(teams.getYear());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            teams.setName(nameTF.getText());
            teams.setTrainer(popTF.getText());
            teams.setCountry(popTA.getText());
            teams.setYear(popTU.getText());
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (nameTF.getText() == null || nameTF.getText().length() == 0) {
            errorMessage += "Неверно введено название команды!\n";
        }
        if (popTF.getText() == null || popTF.getText().length() == 0) {
            errorMessage += "Неверно введен тренер!\n";
        }
        if (popTA.getText() == null || popTA.getText().length() == 0) {
            errorMessage += "Неверно введена страна!\n";
        }
        if (popTU.getText() == null || popTU.getText().length() == 0) {
            errorMessage += "Неверно введён год основания!\n";
        } else {
            try {
                Integer.parseInt(popTU.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Неверно введёны значения (должно быть целое число)!\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Неправильные поля!");
            alert.setHeaderText("Пожалуйста, введите поля верно!");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}

