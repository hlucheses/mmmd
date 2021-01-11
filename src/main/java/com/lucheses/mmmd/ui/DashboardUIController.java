/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucheses.mmmd.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author lucheses
 */
public class DashboardUIController implements Initializable {

    @FXML
    Pane contentArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("../fxml/PerfilUI.fxml"));

            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(DashboardUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fecharPrograma(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimizarPrograma(MouseEvent event) {
    }

    @FXML
    private void irParaPerfil(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../fxml/PerfilUI.fxml"));

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    
    @FXML
    private void irParaFamilia(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../fxml/FamiliaUI.fxml"));

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    
    @FXML
    private void irParaAnimais(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../fxml/AnimaisUI.fxml"));

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    
    @FXML
    private void irParaEconomia(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../fxml/EconomiaUI.fxml"));

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    
    @FXML
    private void irParaAvancado(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../fxml/AvancadoUI.fxml"));

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
}
