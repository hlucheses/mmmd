package com.lucheses.mmmd.ui.dashboard.economia;

import com.jfoenix.controls.JFXTextField;
import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.Sessao;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
 * FXML Controller class
 *
 * @author lucheses
 */
public class EditarRendimentoUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private JFXTextField valorTxt;
    @FXML
    private JFXTextField origemTxt;
    @FXML
    private FontAwesomeIconView closeBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.tornarArrastavel(contentArea);
        valorTxt.setText(Double.toString(Sessao.rendimento.getValor()));
        origemTxt.setText(Sessao.rendimento.getOrigem());
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
    }    
    
    private static void msgSucesso(String str) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("Dados alterados com sucesso!");
        alert.setContentText(str);
        alert.showAndWait();
    }

    @FXML
    private void fecharPrograma(MouseEvent event) {
        Sessao.rendimento = null;
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void mudarOrigem(MouseEvent event) {
        Sessao.rendimento.setOrigem(origemTxt.getText());
        Sessao.rendimento.persistir();
        msgSucesso("Origem do rendimento alterado");
    }

    @FXML
    private void mudarValor(MouseEvent event) {
        Sessao.rendimento.setValor(Double.parseDouble(valorTxt.getText()));
        Sessao.rendimento.persistir();
        msgSucesso("Valor do rendimento alterado");
    }
    
}
