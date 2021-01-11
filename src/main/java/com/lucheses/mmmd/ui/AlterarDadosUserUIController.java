/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucheses.mmmd.ui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lucheses
 */
public class AlterarDadosUserUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private JFXTextField nomeDoMembroTxt;
    @FXML
    private JFXTextField nomeDaFamiliaTxt;
    @FXML
    private JFXTextField telefoneTxt;
    @FXML
    private JFXPasswordField passwordTxt;
    @FXML
    private JFXPasswordField passwordTxt1;
    @FXML
    private FontAwesomeIconView closeBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void fecharPrograma(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void novoMembro(MouseEvent event) {
    }
}
