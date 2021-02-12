package com.lucheses.mmmd.ui.dashboard.economia;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.Credito;
import com.lucheses.mmmd.entidades.Dizimo;
import com.lucheses.mmmd.entidades.Gasto;
import com.lucheses.mmmd.entidades.MembroHumano;
import com.lucheses.mmmd.entidades.PrevisaoMensal;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

/**
 *
 * @author lucheses
 */
public class NovoCreditoUIController implements Initializable {

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
        String localGasto = localTxt.getText();
        String designacao = designacaoTxt.getText();
        double valorGasto = Double.parseDouble(valorTxt.getText());
        Date dataFim = Date.from(dataDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        PrevisaoMensal pm = BaseDeDados.buscarUltimaPrevisao(Sessao.membroHumano.getFamilia());

        
        Credito c = new Credito(localGasto, designacao, valorGasto, dataFim, pm, autorGasto);
        c.persistir();
        new Rendimento(Date.from(LocalDate.now().atStartOfDay(ZoneId.
                systemDefault()).toInstant()), valorGasto, "Crédito",
                autorGasto, pm).persistir();
        sucessoGasto();
        
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }

    public static void sucessoGasto() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayo");
        alert.setHeaderText("Crédito efectuado!");
        alert.showAndWait();
    }
}
