package com.example.football;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;



public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    ObservableList<Teams> teams = FXCollections.observableArrayList();

    public MainApp() {
        teams.add(new Teams(1, "1922", "Спартак", "Абаскаль", "Россия"));
        teams.add(new Teams(2, "1911", "ЦСКА", "Федотов", "Россия"));
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Команды и игроки");
        this.primaryStage.getIcons().add(new Image("file:ball.png"));
        initRootLayout();
        showPersonOverview();
    }

    public ObservableList<Teams> getFacultiesData() {
        return teams;
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = getCityFilePath();
        if (file != null) {
            loadPersonDataFromFile(file);
        }
    }

    public void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("TeamsOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(personOverview);
            OverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public boolean showTeamsEditDialog(Teams city) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("TeamsEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование элемента");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            TeamsEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(city);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public File getCityFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setCityFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            primaryStage.setTitle("Название приложения - " + file.getName());
        } else {
            prefs.remove("filePath");
            primaryStage.setTitle("Название приложения");
        }
    }

    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(TeamsListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            TeamsListWrapper wrapper = (TeamsListWrapper) um.unmarshal(file);
            teams.clear();
            teams.addAll(wrapper.getcities());
            setCityFilePath(file);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не удалось загрузить данные!");
            alert.setContentText("Не удалось загрузить файл:\n" + file.getPath());
            alert.showAndWait();
        }
    }

    public void saveCityDataToFile(File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TeamsListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        TeamsListWrapper wrapper = new TeamsListWrapper();
        wrapper.setcities(teams);
        m.marshal(wrapper, file);
        setCityFilePath(file);
    }

}
