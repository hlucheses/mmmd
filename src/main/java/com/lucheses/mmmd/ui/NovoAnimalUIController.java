/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucheses.mmmd.ui;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lucheses
 */
public class NovoAnimalUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private JFXTextField nomeDoMembroTxt;
    @FXML
    private JFXTextField nomeDaFamiliaTxt;
    @FXML
    private JFXTextField telefoneTxt;
    @FXML
    private DatePicker dataDeNascimentoDP;
    @FXML
    private DatePicker dataDeAquisicaoDP;
    @FXML
    private JFXRadioButton masculinoRBtn;
    @FXML
    private ToggleGroup sexoToggle;
    @FXML
    private JFXRadioButton femininoRBtn;
    @FXML
    private FontAwesomeIconView minimizarBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        masculinoRBtn.setSelected(true);
        dataDeNascimentoDP.setValue(NOW_LOCAL_DATE());
        dataDeAquisicaoDP.setValue(NOW_LOCAL_DATE());
    }

    public static final LocalDate NOW_LOCAL_DATE() {
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    @FXML
    private void fecharPrograma(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
