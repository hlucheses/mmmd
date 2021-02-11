/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucheses.mmmd.ui.dashboard;

import com.jfoenix.controls.JFXButton;
import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lucheses
 */
public class AdminUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private JFXButton criarFamiliaBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void novaFamilia(MouseEvent event) throws IOException {
        if (!BaseDeDados.verificarResponsaveis()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Não é possível criar família");
            alert.setContentText("Não há membros com idade maior que 18 disponíveis");
            alert.showAndWait();
        } else {
            App.novaJanela("fxml/dashboard/admin/NovaFamiliaUI");
        }
    }
    
    @FXML
    private void adicionarMembro(MouseEvent event) throws IOException {
        if (BaseDeDados.haUtilizadoresDisponiveis()) {
            App.novaJanela("fxml/dashboard/admin/EscolherMembroUI");
        } else {
            App.novaJanela("fxml/registo/NovoMembroUI");
        }
    }
    
    @FXML
    private void verFamilias(MouseEvent event) throws IOException {
        if (BaseDeDados.haFamilias()) {
            Sessao.acao = "VER_FAMILIA_ADMIN";
            App.novaJanela("fxml/dashboard/admin/EscolherFamiliaUI");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Não existem famílias!");
            alert.showAndWait();
        }
    }

    @FXML
    private void eliminarFamilia(MouseEvent event) {
        
    }

}
