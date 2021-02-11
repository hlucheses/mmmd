package com.lucheses.mmmd.ui.dashboard.economia;

import com.jfoenix.controls.JFXComboBox;
import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.PrevisaoMensal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author lucheses
 */
public class VerPrevisoesUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private Label balancoMensalLbl;
    @FXML
    private Label totalGastosLbl;
    @FXML
    private Label totalRendimentosLbl;
    @FXML
    private Label poupancaLbl;
    @FXML
    private JFXComboBox<PrevisaoMensal> seleccionarPrevisaoCombo;
    private PrevisaoMensal previsaoMensal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.tornarArrastavel(contentArea);
        seleccionarPrevisaoCombo.setItems(FXCollections.observableArrayList(BaseDeDados.
                buscarPrevisoesFamilia(Sessao.membroHumano.getFamilia())));
        seleccionarPrevisaoCombo.setConverter(new StringConverter<PrevisaoMensal>() {
            @Override
            public String toString(PrevisaoMensal f) {
                return f.getDataPrevisao().toString();
            }

            @Override
            public PrevisaoMensal fromString(String string) {
                return null;
            }
        });
        seleccionarPrevisaoCombo.valueProperty().addListener((obs, oldItem, newItem) -> {
            previsaoMensal = newItem;
            poupancaLbl.setText(Double.toString(previsaoMensal.getPoupanca()) + " AOA");
            totalRendimentosLbl.setText(Double.toString(previsaoMensal.calcularRendimentos()) + " AOA");
            totalGastosLbl.setText(Double.toString(previsaoMensal.calcularGastos()) + " AOA");
            balancoMensalLbl.setText(Double.toString(previsaoMensal.getPoupanca() - previsaoMensal.calcularGastos()
            + previsaoMensal.calcularRendimentos()) + " AOA");
        });
    }

    @FXML
    private void fecharPrograma(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

}
