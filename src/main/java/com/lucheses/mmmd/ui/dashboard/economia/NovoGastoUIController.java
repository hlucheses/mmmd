package com.lucheses.mmmd.ui.dashboard.economia;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.Dizimo;
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
import javafx.scene.control.CheckBox;
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
    private JFXTextField instituicaoTxt;
    @FXML
    private JFXComboBox<MembroHumano> seleccionarAutorCombo;
    private MembroHumano autorGasto;
    @FXML
    private CheckBox dizimoCheckB;
    @FXML
    private CheckBox creditoCheckB;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        valorTxt.setText("0");
        instituicaoTxt.setVisible(false);

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

        dizimoCheckB.setOnAction(action -> {
            if (dizimoCheckB.isSelected()) {
                valorTxt.setText("0");
                valorTxt.setVisible(false);
                instituicaoTxt.setVisible(true);
                designacaoTxt.setVisible(false);
                localTxt.setVisible(false);
            } else {
                instituicaoTxt.setVisible(false);
                valorTxt.setVisible(true);
                designacaoTxt.setVisible(true);
                localTxt.setVisible(true);
            }

        });
    }

    @FXML
    private void fecharPrograma(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void novoGasto(MouseEvent event) {

        String localGasto = localTxt.getText();
        String designacao = designacaoTxt.getText();

        if (dizimoCheckB.isSelected()) {
            
            String instituicao = instituicaoTxt.getText();
            
            if (autorGasto == null || instituicao.equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ayo");
                alert.setHeaderText("Preencha todos os campos");
                alert.showAndWait();
            }
            
            Dizimo d = new Dizimo(Date.from(dataDP.getValue().
                    atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    autorGasto, instituicao,
                    BaseDeDados.buscarUltimaPrevisao(Sessao.membroHumano.getFamilia()));
            
            if (d.getValor() + d.getPrevisao().calcularGastos() > d.getPrevisao().calcularRendimentos()) {
                falhaGasto();
            } else {
                sucessoGasto();
                d.persistir();
            }
            
        } else {
            String valor = valorTxt.getText();

            if (designacao.equals("") || localGasto.equals("") || autorGasto == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ayo");
                alert.setHeaderText("Preencha todos os campos");
                alert.showAndWait();
            }

            if (valor.equals("")) {
                valor = "0";
            }

            Gasto g = new Gasto(Date.from(dataDP.getValue().
                    atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Double.parseDouble(valor), designacao,
                    localGasto, autorGasto,
                    BaseDeDados.buscarUltimaPrevisao(Sessao.membroHumano.getFamilia()));
            
            if (g.getValor() + g.getPrevisao().calcularGastos() > g.getPrevisao().calcularRendimentos()) {
                falhaGasto();
            } else {
                sucessoGasto();
                g.persistir();
            }
            
        }

        ((Node) (event.getSource())).getScene().getWindow().hide();

    }
    
    public static void sucessoGasto() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayo");
        alert.setHeaderText("Gasto efectuado!");
        alert.showAndWait();
    }
    
    public static void falhaGasto() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayo");
        alert.setHeaderText("Impossível efectuar gasto, saldo daria negativo!");
        alert.showAndWait();
    }
}
