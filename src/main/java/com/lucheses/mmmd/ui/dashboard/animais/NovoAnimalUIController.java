package com.lucheses.mmmd.ui.dashboard.animais;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.AnimalDeEstimacao;
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
import javafx.scene.control.DatePicker;
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
    private DatePicker dataDeNascimentoDP;
    @FXML
    private DatePicker dataDeAquisicaoDP;
    @FXML
    private JFXRadioButton masculinoRBtn;
    @FXML
    private JFXTextField nomeTxt;
    @FXML
    private JFXTextField especieTxt;
    @FXML
    private JFXTextField racaTxt;

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
    private void novoAnimal(MouseEvent event) {
        String nome = nomeTxt.getText();
        String especie = especieTxt.getText();
        String raca = racaTxt.getText();
        Date dataDeNascimento = Date.from(dataDeNascimentoDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dataDeAquisicao = Date.from(dataDeAquisicaoDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        char sexo = masculinoRBtn.isSelected() ? 'M' : 'F';
        new AnimalDeEstimacao(nome, especie, raca, dataDeNascimento, dataDeAquisicao, sexo, Sessao.membroHumano.getFamilia()).persistir();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText("Animal inserido na família com sucesso!");
        alert.showAndWait();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void fecharPrograma(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
