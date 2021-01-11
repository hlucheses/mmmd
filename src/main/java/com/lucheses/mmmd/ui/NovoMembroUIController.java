/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucheses.mmmd.ui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import static com.lucheses.mmmd.conf.Sessao.familia;
import com.lucheses.mmmd.entidades.Familia;
import com.lucheses.mmmd.entidades.Membro;
import com.lucheses.mmmd.entidades.MembroHumano;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author lucheses
 */
public class NovoMembroUIController implements Initializable {

    @FXML
    private JFXTextField nomeDoMembroTxt;
    @FXML
    private JFXTextField nomeDaFamiliaTxt;
    @FXML
    private JFXTextField telefoneTxt;
    @FXML
    private DatePicker dataDeNascimentoDP;
    @FXML
    private JFXRadioButton masculinoRBtn;
    @FXML
    private AnchorPane contentArea;
    @FXML
    private JFXPasswordField passwordTxt;
    @FXML
    private JFXPasswordField passwordTxt1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.tornarArrastavel(contentArea);
        masculinoRBtn.setSelected(true);
        dataDeNascimentoDP.setValue(NOW_LOCAL_DATE());
    }

    public static final LocalDate NOW_LOCAL_DATE() {
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
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
    private void novoMembro(MouseEvent event) throws IOException {
        String nomeDoMembro = nomeDoMembroTxt.getText();
        String nomeDaFamilia = nomeDaFamiliaTxt.getText();
        String telefone = telefoneTxt.getText();
        Date dataDeNascimento = Date.from(dataDeNascimentoDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        char sexo = masculinoRBtn.isSelected() ? 'M' : 'F';

        if (nomeDoMembro.equals("") || nomeDaFamilia.equals("") || telefone.equals("")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Ocorreu um erro ao tentar registar um membro!");
            alert.setContentText("Preencha todos os campos!");
            alert.showAndWait();
        } else {

            if (!BaseDeDados.haFamilias()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Espera um momento");
                alert.setHeaderText("Parece que não existem famílias!");
                alert.setContentText("Terá de criar uma nova para proceder com o registo!");
                alert.showAndWait();
                
                
            }
            Sessao.membro = new Membro(nomeDoMembro, dataDeNascimento, sexo);
                //Sessao.membro.tornarResponsavel();
                //Sessao.membroHumano.setUtilizador(Sessao.utilizador);
                
                App.novaJanela("fxml/NovaFamiliaUI");
                ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }
}
