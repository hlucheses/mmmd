/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private JFXTextField emailTxt;
    @FXML
    private JFXPasswordField passwordTxt;
    @FXML
    private JFXPasswordField confirmPasswordTxt;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    }

    @FXML
    private void registar(MouseEvent event) throws IOException {

        String email = emailTxt.getText();
        String password = passwordTxt.getText();
        String confirmPassword = confirmPasswordTxt.getText();

        if (validarEmail(email) && validarPassword(password, confirmPassword)) {
            Sessao.utilizador = new Utilizador(email, password);
            Sessao.utilizador.persistir();

            App.novaJanela("fxml/NovoMembroUI");
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } else {
            passwordTxt.setText("");
            confirmPasswordTxt.setText("");
        }
    }

    @FXML
    private void abrirNovoMembroUI(MouseEvent event) {

    }

    private boolean validarEmail(String email) {
        final Pattern EMAIL_VALIDO
                = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = EMAIL_VALIDO.matcher(email);
        boolean emailEValido = matcher.find();

        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText("Ocorreu um erro ao tentar criar uma conta!");

        String msgAlert = "Email Inválido!";

        if (emailEValido) {
            if (BaseDeDados.emailJaExiste(email)) {
                msgAlert += "\nJá existe uma conta com este email!";
                emailEValido = false;
            }
        }

        if (!emailEValido) {
            alert.setContentText(msgAlert);
            alert.showAndWait();
        }

        return emailEValido;
    }

    private static boolean validarPassword(String password, String confirmPassword) {
        final Pattern PASSWORD_VALIDA_TAMANHO = Pattern.compile("^.{8,16}$");
        final Pattern PASSWORD_VALIDA_CARACTERES
                = Pattern.compile("^(?=.*?[0-9])(?=.*?[A-Za-z]).+$");
        Matcher matcherTam = PASSWORD_VALIDA_TAMANHO.matcher(password);
        Matcher matcherChar = PASSWORD_VALIDA_CARACTERES.matcher(password);

        boolean passwordTamVal = matcherTam.find();
        boolean passwordCharVal = matcherChar.find();
        boolean passwordEqualsConfirm = password.equals(confirmPassword);
        boolean passwordValida = passwordTamVal && passwordCharVal && passwordEqualsConfirm;

        if (!passwordValida) {

            String strPwErro = "";

            if (!passwordTamVal) {
                strPwErro += "A password deve ter de 8 a 16 caracteres!\n";
            }

            if (!passwordCharVal) {
                strPwErro += "A password deve ter letras e números!\n";
            }

            if (!passwordEqualsConfirm) {
                strPwErro += "As passwords não correspondem!\n";
            }

            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Ocorreu um erro ao tentar criar uma conta!");
            alert.setContentText("Password inválida!\n" + strPwErro);
            alert.showAndWait();
        }

        return passwordValida;
    }

}
