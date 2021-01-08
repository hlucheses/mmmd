/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucheses.mmmd.ui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.lucheses.mmmd.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author lucheses
 */
public class LoginUIController implements Initializable {

    @FXML
    private Pane contentArea;
    @FXML
    private JFXTextField emailTxt;
    @FXML
    private JFXPasswordField passwordTxt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void fecharPrograma(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void abrirRegistar(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../fxml/RegistarUI.fxml"));

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void minimizarPrograma(MouseEvent event) {
    }

    @FXML
    private void login(MouseEvent event) throws IOException {

        String email = emailTxt.getText();
        String password = passwordTxt.getText();

        if (validarUtilizador(email, password)) {

            Scene scene = new Scene(App.loadFXML("fxml/NovoMembro"));
            scene.getStylesheets().add(getClass().getResource("styles/style.css").toExternalForm());

            App.stage.setScene(scene);
            App.stage.show();

        }
    }

    private static boolean validarUtilizador(String email, String password) {
        
        return false;
    }
}
