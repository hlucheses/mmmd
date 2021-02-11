package com.lucheses.mmmd.ui.dashboard.perfil;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.Utilizador;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class AlterarDadosUserUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private JFXTextField nomeDoMembroTxt;
    @FXML
    private JFXTextField telefoneTxt;
    @FXML
    private JFXTextField emailTxt;
    @FXML
    private JFXPasswordField passwordTxt;
    @FXML
    private JFXPasswordField confirmPasswordTxt;
    

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
        Sessao.membroHumano.setNome(nomeDoMembroTxt.getText());
        Sessao.membroHumano.persistir();
        msgSucesso("Nome do utilizador alterado");
    }

    @FXML
    private void mudarTelefone(MouseEvent event) {
        Sessao.membroHumano.setTelefone(telefoneTxt.getText());
        Sessao.membroHumano.persistir();
        msgSucesso("Telefone do utilizador alterado");
    }

    @FXML
    private void mudarEmail(MouseEvent event) {
        Sessao.membroHumano.setEmail(emailTxt.getText());
        Sessao.membroHumano.persistir();
        msgSucesso("E-mail do utilizador alterado");
    }

    @FXML
    private void mudarPassword(MouseEvent event) {
        String password = passwordTxt.getText();
        String confirmPassword = confirmPasswordTxt.getText();

        if (Utilizador.validarPassword(password, confirmPassword)) {
            Sessao.utilizador.setPassword(password);
            Sessao.utilizador.persistir();
            msgSucesso("Palavra-passe alterada");
        }
        passwordTxt.setText("");
        confirmPasswordTxt.setText("");
        Sessao.membroHumano.setNome(nomeDoMembroTxt.getText());

    }
}
