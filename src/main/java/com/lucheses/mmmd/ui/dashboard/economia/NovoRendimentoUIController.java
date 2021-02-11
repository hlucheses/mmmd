package com.lucheses.mmmd.ui.dashboard.economia;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.Gasto;
import com.lucheses.mmmd.entidades.MembroHumano;
import com.lucheses.mmmd.entidades.Rendimento;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

/**
 *
 * @author lucheses
 */
public class NovoRendimentoUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private DatePicker dataDP;
    @FXML
    private JFXTextField valorTxt;
    @FXML
    private JFXTextField origemTxt;
    @FXML
    private JFXComboBox<MembroHumano> seleccionarBeneficiarioCombo;
    private MembroHumano beneficiarioRendimento;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        valorTxt.setText("0");

        seleccionarBeneficiarioCombo.setItems(FXCollections.observableArrayList(BaseDeDados.
                getMembrosFamilia(Sessao.membroHumano.getFamilia())));
        seleccionarBeneficiarioCombo.setConverter(new StringConverter<MembroHumano>() {
            @Override
            public String toString(MembroHumano f) {
                return f.getNome();
            }

            @Override
            public MembroHumano fromString(String string) {
                return null;
            }
        });
        seleccionarBeneficiarioCombo.valueProperty().addListener((obs, oldItem, newItem) -> {
            beneficiarioRendimento = newItem;
        });
        valorTxt.textProperty()
                .addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                            String newValue
                    ) {
                        if (!newValue.matches("\\d*")) {
                            valorTxt.setText(oldValue);
                        }
                    }
                }
                );

        dataDP.setValue(LocalDate.now());
    }

    @FXML
    private void fecharPrograma(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void novoRendimento(MouseEvent event) {
        String valor = valorTxt.getText();
        String origem = origemTxt.getText();

        if (origem.equals("") || beneficiarioRendimento == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ayo");
            alert.setHeaderText("Preencha todos os campos");
            alert.showAndWait();
        }
        
        if (valor.equals("")) {
            valor = "0";
        } 

        new Rendimento(Date.from(dataDP.getValue().
                atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Double.parseDouble(valor),
                origem, beneficiarioRendimento,
                BaseDeDados.buscarUltimaPrevisao(Sessao.membroHumano.getFamilia())).persistir();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayo");
        alert.setHeaderText("Rendimento adicionado!");
        alert.showAndWait();
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }
}