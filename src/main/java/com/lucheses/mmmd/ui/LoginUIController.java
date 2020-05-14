/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucheses.mmmd.ui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author lucheses
 */
public class LoginUIController implements Initializable {
    
    private Label label;
    @FXML
    private Pane contentArea;
    @FXML
    private Label criarConta;
    @FXML
    private FontAwesomeIconView minimizarBtn;
    
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
    private void minimizarPrograma(MouseEvent event) {}

    @FXML
    private void login(MouseEvent event) {
    }
}
