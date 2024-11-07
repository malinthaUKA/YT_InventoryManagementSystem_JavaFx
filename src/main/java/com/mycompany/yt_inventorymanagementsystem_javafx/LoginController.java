/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.yt_inventorymanagementsystem_javafx;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.xml.transform.Result;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class LoginController implements Initializable {

    
    
    @FXML
    private Button close_btn;

    @FXML
    private Button login_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password_txt;

    @FXML
    private TextField username_txt;

    /**
     * Initializes the controller class.
     */
    
    // DATABASE TOOLS
    private Connection con;
    private PreparedStatement prepare;
    private ResultSet result;
    private double x = 0;
    private double y = 0;
    
    public void loginAdmin(){
        
        String sql = "select * from admin where username = ? and password = ?";
        
        con = databaseHandler.connectDb();
        
        try {
            prepare = con.prepareStatement(sql);
            prepare.setString(1, username_txt.getText());
            prepare.setString(2, password_txt.getText());
            
            result = prepare.executeQuery();
            
            Alert alert;
            
            if(username_txt.getText().isEmpty() || password_txt.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                
                if(result.next()){
                    
                    // IF CORRECT USERNAME AND PASSWORD, THEN PROCEED TO DASHBOARD FORM
                    alert =  new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();
                    
                    // HIDE THE LOGIN FORM
                    login_btn.getScene().getWindow().hide();
                    
                    // LINK DASHBOARD FORM
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });
                    
                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });
                    
                    stage.initStyle(StageStyle.TRANSPARENT);
                    
                    stage.setScene(scene);
                    stage.show();
                }else{
                    // IF WRONG THEN THE ERROR MESSAGE WILL APPEAR
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ERROR Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void close(){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
