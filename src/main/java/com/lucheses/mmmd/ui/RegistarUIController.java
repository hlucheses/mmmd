package com.lucheses.mmmd.ui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.Utilizador;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lucheses
 */
public class RegistarUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private FontAwesomeIconView minimizarBtn;
    @FXML
    private JFXTextField usernameTxt;
    @FXML
    private JFXPasswordField passwordTxt;
    @FXML
    private JFXPasswordField confirmPasswordTxt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void fecharPrograma(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void voltarParaLogin(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/LoginUI.fxml"));
        App.stage.getScene().setRoot(root);
    }

    @FXML
    private void minimizarPrograma(MouseEvent event) {
        App.stage.setIconified(true);
    }

    @FXML
    private void registar(MouseEvent event) throws IOException {

        String username = usernameTxt.getText();
        String password = passwordTxt.getText();
        String confirmPassword = confirmPasswordTxt.getText();

        if (validarUsername(username) && Utilizador.validarPassword(password, confirmPassword)) {
            Sessao.utilizador = new Utilizador(username, password);
            Sessao.utilizador.persistir();
            Sessao.utilizador = BaseDeDados.getUtilizadorByUsername(username);

            App.novaJanela("fxml/registo/NovoMembroUI");
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } else {
            passwordTxt.setText("");
            confirmPasswordTxt.setText("");
        }
    }

    @FXML
    private void abrirNovoMembroUI(MouseEvent event) {

    }

    private boolean validarUsername(String username) {
        final Pattern USERNAME_VALIDO =
                Pattern.compile("^(?=.*?[A-Za-z])[a-zA-Z0-9_]{3,30}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = USERNAME_VALIDO.matcher(username);
        boolean usernameEValido = matcher.find();

        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText("Ocorreu um erro ao tentar criar uma conta!");

        String msgAlert = "Username Inválido!";

        if (usernameEValido) {
            if (BaseDeDados.usernameJaExiste(username)) {
                msgAlert += "\nJá existe uma conta com este username!";
                usernameEValido = false;
            }
        }

        if (!usernameEValido) {
            alert.setContentText(msgAlert);
            alert.showAndWait();
        }

        return usernameEValido;
    }
}
