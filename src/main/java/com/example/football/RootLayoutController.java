package com.example.football;

import java.io.File;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javax.xml.bind.JAXBException;

public class RootLayoutController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleNew() {
        mainApp.getFacultiesData().clear();
        mainApp.setCityFilePath(null);
    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }

    @FXML
    private void handleSave() throws JAXBException {
        File personFile = mainApp.getCityFilePath();
        if (personFile != null) {
            mainApp.saveCityDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    @FXML
    private void handleSaveAs() throws JAXBException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.saveCityDataToFile(file);
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}

