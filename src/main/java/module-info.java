module com.mycompany.yt_inventorymanagementsystem_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.base;

    opens com.mycompany.yt_inventorymanagementsystem_javafx to javafx.fxml;
    exports com.mycompany.yt_inventorymanagementsystem_javafx;
}
