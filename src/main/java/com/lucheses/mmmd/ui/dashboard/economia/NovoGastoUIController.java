package com.lucheses.mmmd.ui.dashboard.economia;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.Gasto;
import com.lucheses.mmmd.entidades.MembroHumano;
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
public class NovoGastoUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private DatePicker dataDP;
    @FXML
    private JFXTextField valorTxt;
    @FXML
    private JFXTextField designacaoTxt;
    @FXML
    private JFXTextField localTxt;
    @FXML
    private JFXComboBox<MembroHumano> seleccionarAutorCombo;
    private MembroHumano autorGasto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        valorTxt.setText("0");

        seleccionarAutorCombo.setItems(FXCollections.observableArrayList(BaseDeDados.
                getMembrosFamilia(Sessao.membroHumano.getFamilia())));
        seleccionarAutorCombo.setConverter(new StringConverter<MembroHumano>() {
            @Override
            public String toString(MembroHumano f) {
                return f.getNome();
            }

            @Override
            public MembroHumano fromString(String string) {
                return null;
            }
        });
        seleccionarAutorCombo.valueProperty().addListener((obs, oldItem, newItem) -> {
            autorGasto = newItem;
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
    private void novoGasto(MouseEvent event) {
        String designacao = designacaoTxt.getText();
        String valor = valorTxt.getText();
        String localGasto = localTxt.getText();

        if (designacao.equals("") || localGasto.equals("") || autorGasto == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ayo");
            alert.setHeaderText("Preencha todos os campos");
            alert.showAndWait();
        }
        
        if (valor.equals("")) {
            valor = "0";
        } 

        new Gasto(Date.from(dataDP.getValue().
                atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Double.parseDouble(valor), designacao,
                localGasto, autorGasto,
                BaseDeDados.buscarUltimaPrevisao(Sessao.membroHumano.getFamilia())).persistir();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayo");
        alert.setHeaderText("Gasto efectuado!");
        alert.showAndWait();
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }
}
