package com.lucheses.mmmd.ui.dashboard.economia;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.PrevisaoMensal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author lucheses
 */
public class NovaPrevisaoUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private JFXRadioButton masculinoRBtn;
    @FXML
    private JFXTextField poupancaTxt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        poupancaTxt.setText("0");
        poupancaTxt.textProperty()
                .addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                            String newValue
                    ) {
                        if (!newValue.matches("\\d*")) {
                            poupancaTxt.setText(oldValue);
                        }
                    }
                }
                );
    }

    @FXML
    private void fecharPrograma(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void novaPrevisao(MouseEvent event) {
        new PrevisaoMensal(Double.parseDouble(poupancaTxt.getText()),
                PrevisaoMensal.dataPrevisao(Sessao.membroHumano.getFamilia()),
                Sessao.membroHumano.getFamilia()).
                persistir();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayo");
        alert.setHeaderText("Previsão criada!");
        alert.showAndWait();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
