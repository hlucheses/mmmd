package com.lucheses.mmmd.ui.registo;

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
    private DatePicker dataDeNascimentoDP;
    @FXML
    private JFXRadioButton masculinoRBtn;
    @FXML
    private JFXRadioButton femininoRBtn;
    @FXML
    private AnchorPane contentArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if (!Sessao.utilizador.isSet() && !Sessao.estaDefinido(Sessao.membroHumano)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Conta criada!");
            alert.setContentText("A conta foi registrada, mas precisa de inserir os dados do membro da família!");
            alert.showAndWait();
        }

        App.tornarArrastavel(contentArea);
        masculinoRBtn.setSelected(true);
        //dataDeNascimentoDP.setValue(NOW_LOCAL_DATE());

        if (Sessao.membroHumano != null) {
            nomeDoMembroTxt.setText(Sessao.membroHumano.getNome());
            telefoneTxt.setText(Sessao.membroHumano.getTelefone());
            //dataDeNascimentoDP.setValue(Sessao.membroHumano.getDataDeNascimento().toInstant()
              //      .atZone(ZoneId.systemDefault())
                //    .toLocalDate());
            if (Sessao.membroHumano.getSexo() == 'F') {
                masculinoRBtn.setSelected(false);
                femininoRBtn.setSelected(true);
            }
        }

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
        String telefone = telefoneTxt.getText();
        Date dataDeNascimento = Date.from(dataDeNascimentoDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        char sexo = masculinoRBtn.isSelected() ? 'M' : 'F';

        if (nomeDoMembro.equals("")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Ocorreu um erro ao tentar registar um membro!");
            alert.setContentText("O membro da família tem de ter nome" + dataDeNascimentoDP.getValue().toString());
            alert.showAndWait();
        } else {

            Sessao.membroHumano = new MembroHumano(nomeDoMembro, dataDeNascimento, sexo, telefone);
            Sessao.membroHumano.setUtilizador(Sessao.utilizador);

            if (!BaseDeDados.haFamilias()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Espera um momento");
                alert.setHeaderText("Parece que não existem famílias!");
                alert.setContentText("Terá de criar uma nova para proceder com o registo!");
                alert.showAndWait();
                Sessao.membroHumano.tornarResponsavel();
                App.novaJanela("fxml/registo/NovaFamiliaUI");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else {
                App.novaJanela("fxml/registo/EscolherFamiliaUI");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }

        }
    }
}
