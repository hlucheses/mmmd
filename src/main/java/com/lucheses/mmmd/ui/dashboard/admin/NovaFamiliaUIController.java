package com.lucheses.mmmd.ui.dashboard.admin;

import com.jfoenix.controls.JFXTextField;
import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.Familia;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author lucheses
 */
public class NovaFamiliaUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private JFXTextField nomeDaFamiliaTxt;
    @FXML
    private JFXTextField telefoneDeCasaTxt;
    @FXML
    private JFXTextField enderecoTxt;
    @FXML
    private JFXTextField bairroTxt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.tornarArrastavel(contentArea);
    }

    @FXML
    private void fecharPrograma(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void minimizarPrograma(MouseEvent event) {
        App.stage.setIconified(true);
    }

    @FXML
    private void fazerNada(MouseEvent event) {

    }

    @FXML
    private void novaFamilia(MouseEvent event) throws IOException {
        String nomeDaFamilia = nomeDaFamiliaTxt.getText();
        String telefoneDeCasa = telefoneDeCasaTxt.getText();
        String endereco = enderecoTxt.getText();
        String bairro = bairroTxt.getText();

        if (nomeDaFamilia.equals("") || telefoneDeCasa.equals("") || endereco.equals("") || bairro.equals("")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Ocorreu um erro ao tentar registar um membro!");
            alert.setContentText("Preencha todos os campos!");
            alert.showAndWait();
        } else {
            Sessao.familia = new Familia(nomeDaFamilia, telefoneDeCasa, endereco, bairro);
            App.novaJanela("fxml/dashboard/admin/EscolherMembroResponsavelUI");
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }
}
