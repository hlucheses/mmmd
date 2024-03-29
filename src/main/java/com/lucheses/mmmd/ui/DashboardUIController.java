package com.lucheses.mmmd.ui;

import com.jfoenix.controls.JFXButton;
import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.Sessao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author lucheses
 */
public class DashboardUIController implements Initializable {

    @FXML
    Pane contentArea;
    @FXML
    AnchorPane parent;
    @FXML
    JFXButton avancadoBtn;
    @FXML
    Pane userSettings;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.tornarArrastavel(parent);

        

        String ecraInicial = "../fxml/dashboard/PerfilUI.fxml";

        if (Sessao.utilizador.eAdmin()) {
            ecraInicial = "../fxml/dashboard/AdminUI.fxml";
            userSettings.setVisible(false);
        } else if (!Sessao.membroHumano.eResponsavel()) {
            avancadoBtn.setVisible(false);
        }

        try {

            Parent fxml = FXMLLoader.load(getClass().getResource(ecraInicial));

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
        App.stage.setIconified(true);
    }

    @FXML
    private void irParaPerfil(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../fxml/dashboard/PerfilUI.fxml"));

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void irParaFamilia(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../fxml/dashboard/FamiliaUI.fxml"));

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void irParaAnimais(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../fxml/dashboard/AnimaisUI.fxml"));

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void irParaEconomia(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../fxml/dashboard/EconomiaUI.fxml"));

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void irParaAvancado(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../fxml/dashboard/AvancadoUI.fxml"));

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void sair(MouseEvent event) throws IOException {
        Sessao.terminar();
        App.novaJanela("fxml/LoginUI");
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
