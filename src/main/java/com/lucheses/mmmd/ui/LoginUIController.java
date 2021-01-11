/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucheses.mmmd.ui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.Utilizador;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author lucheses
 */
public class LoginUIController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private Pane contentArea;
    @FXML
    private JFXTextField emailTxt;
    @FXML
    private JFXPasswordField passwordTxt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.tornarArrastavel(parent);
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
        App.stage.setIconified(true);
    }

    @FXML
    private void login(MouseEvent event) throws IOException {

        Utilizador u = new Utilizador(emailTxt.getText(), passwordTxt.getText());
        Sessao.utilizador = u;
        
        if (u.verificarCredenciais()) {
            //u = BaseDeDados.getUtilizadorByEmail(emailTxt.getText());
            
            //if (u.estaDefinido()) {
                
            //} else {
                App.novaJanela("fxml/NovoMembroUI");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            //}
        }
    }

    
}
