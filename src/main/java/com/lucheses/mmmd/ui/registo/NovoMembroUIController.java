package com.lucheses.mmmd.ui.registo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
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
    private JFXTextField telefoneTxt;
    @FXML
    private JFXTextField emailTxt;
    @FXML
    private DatePicker dataDeNascimentoDP;
    @FXML
    private JFXRadioButton masculinoRBtn;
    @FXML
    private JFXRadioButton femininoRBtn;
    @FXML
    private JFXButton voltarBtn;
    @FXML
    private AnchorPane contentArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        voltarBtn.setVisible(false);
        if (!Sessao.utilizador.isSet() && !Sessao.estaDefinido(Sessao.membroHumano)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Conta criada!");
            alert.setContentText("A conta foi registrada, mas precisa de inserir os dados!");
            alert.showAndWait();
        }

        if (Sessao.utilizador.isSet()) {
            voltarBtn.setVisible(true);
        }

        if (Sessao.utilizador.eAdmin() && !BaseDeDados.verificarResponsaveis()) {
            voltarBtn.setVisible(false);
        }
        
        if (Sessao.acao.equals("FROM_ESCOLHER_MEMBRO")) {
            voltarBtn.setVisible(true);
            Sessao.acao = "DEFAULT";
        }

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
    private void fecharPrograma(MouseEvent event) throws IOException {
        if (!Sessao.utilizador.isSet()) {
            Sessao.terminar();
            App.novaJanela("fxml/LoginUI");
        }
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void minimizarPrograma(MouseEvent event) {
        App.stage.setIconified(true);
    }

    @FXML
    private void voltar(MouseEvent event) throws IOException {
        App.novaJanela("fxml/dashboard/admin/EscolherMembroUI");
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void novoMembro(MouseEvent event) throws IOException {
        String nomeDoMembro = nomeDoMembroTxt.getText();
        String telefone = telefoneTxt.getText();
        String email = emailTxt.getText();
        Date dataDeNascimento = Date.from(dataDeNascimentoDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        char sexo = masculinoRBtn.isSelected() ? 'M' : 'F';

        if (nomeDoMembro.equals("")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Ocorreu um erro ao tentar registar um membro!");
            alert.setContentText("O membro da família tem de ter nome");
            alert.showAndWait();
        } else {
            Sessao.membroHumano = new MembroHumano(nomeDoMembro, dataDeNascimento, sexo, telefone, email);

            if (!Sessao.utilizador.isSet()) {
                Sessao.utilizador.setSet(true);
                Sessao.utilizador.persistir();
                Sessao.membroHumano.setUtilizador(Sessao.utilizador);
                Sessao.membroHumano.persistir();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Aviso");
                alert.setHeaderText("Membro sem família");
                alert.setContentText("Antes de poder usar o sistema peça\n"
                        + "ao administrador ou membro responsável para\n"
                        + "incluí-lo na sua família");
                alert.showAndWait();
                Sessao.terminar();
                App.novaJanela("fxml/LoginUI");
            } else if (Sessao.utilizador.eAdmin()) {
                App.novaJanela("fxml/dashboard/admin/EscolherFamiliaUI");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aviso");
                alert.setHeaderText("Utilizador inserido na família com sucesso!");
                alert.showAndWait();
                Sessao.membroHumano.setFamilia(Sessao.familia);
                Sessao.membroHumano.persistir();
            }
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }
}
