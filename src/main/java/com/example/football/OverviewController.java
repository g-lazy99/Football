package com.example.football;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OverviewController {

    @FXML
    private TableView<Teams> TeamsPanel;
    @FXML
    private TableColumn<Teams,String> TeamsNames;
    @FXML
    private Label cName, cTrainer, cCountry, cYear;

    private MainApp mainApp;

    public OverviewController() {
    }

    @FXML
    private void initialize() {
        TeamsNames.setCellValueFactory(new PropertyValueFactory<>("name"));
        TeamsPanel.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCityDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        TeamsPanel.setItems(mainApp.getFacultiesData());
    }

    private void showCityDetails(Teams city){
        if (city != null)
        {
            cName.setText(city.getName());
            cTrainer.setText(city.getTrainer());
            cCountry.setText(city.getCountry());
            cYear.setText(city.getYear());
        }
    }

    @FXML
    private void handleDeleteCity() {
        int selectedIndex = TeamsPanel.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            TeamsPanel.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ничего не выбрано!");
            alert.setHeaderText("Не выбрана команда!");
            alert.setContentText("Выберите команду в таблице для удаления!");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewCity() {
        Teams tempcity = new Teams();
        boolean okClicked = mainApp.showTeamsEditDialog(tempcity);
        if (okClicked) {
            mainApp.getFacultiesData().add(tempcity);
        }
    }

    @FXML
    private void handleEditCity() {
        Teams selectedcity = TeamsPanel.getSelectionModel().getSelectedItem();
        if (selectedcity != null) {
            boolean okClicked = mainApp.showTeamsEditDialog(selectedcity);
            if (okClicked) {
                showCityDetails(selectedcity);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ничего не выбрано!");
            alert.setHeaderText("Не выбран фильм!");
            alert.setContentText("Выберите фильм в таблице!");
            alert.showAndWait();
        }
    }
}
