module textprocessor.textprocessor {
    requires javafx.controls;
    requires javafx.fxml;


    opens textprocessor.textprocessor to javafx.fxml;
    exports textprocessor.textprocessor;
}