module org.example.garden {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.garden to javafx.fxml;
    exports org.example.garden;
}