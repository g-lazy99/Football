module com.example.football {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;
    requires java.xml.bind;
    opens com.example.football to javafx.fxml;
    exports com.example.football;
}