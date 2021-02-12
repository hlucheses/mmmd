package com.lucheses.mmmd.ui.dashboard.familia;

import com.jfoenix.controls.JFXTextField;
import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.Sessao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lucheses
 */
public class AlterarDadosFamiliaUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private JFXTextField nomeTxt;
    @FXML
    private JFXTextField bairroTxt;
    @FXML
    private JFXTextField enderecoTxt;
    @FXML
    private JFXTextField telefoneTxt;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.tornarArrastavel(contentArea);
    }    

    @FXML
    private void fecharPrograma(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
    private static void msgSucesso(String str) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("Dados alterados com sucesso!");
        alert.setContentText(str);
        alert.showAndWait();
    }

    @FXML
    private void mudarNome(MouseEvent event) {
        Sessao.membroHumano.getFamilia().setNome(nomeTxt.getText());
        Sessao.membroHumano.getFamilia().persistir();
        msgSucesso("Nome da família alterado");
    }
    
    @FXML
    private void mudarTelefone(MouseEvent event) {
        Sessao.membroHumano.getFamilia().setTelefone(telefoneTxt.getText());
        Sessao.membroHumano.getFamilia().persistir();
        msgSucesso("Telefone da família alterado");
    }
    
    @FXML
    private void mudarEndereco(MouseEvent event) {
        Sessao.membroHumano.getFamilia().setEndereco(enderecoTxt.getText());
        Sessao.membroHumano.getFamilia().persistir();
        msgSucesso("Endereço da família alterado");
    }
    
    @FXML
    private void mudarBairro(MouseEvent event) {
        Sessao.membroHumano.getFamilia().setBairro(bairroTxt.getText());
        Sessao.membroHumano.getFamilia().persistir();
        msgSucesso("Bairro da família alterado");
    }
    
}
