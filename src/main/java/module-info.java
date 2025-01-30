module com.example.hangmangamefx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hangmangamefx to javafx.fxml;
    exports com.example.hangmangamefx;
}