package com.lucheses.mmmd.ui.dashboard.admin;

import com.jfoenix.controls.JFXComboBox;
import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.MembroHumano;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author lucheses
 */
public class EscolherMembroUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private JFXComboBox<MembroHumano> seleccionarMembroCombo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.tornarArrastavel(contentArea);
        seleccionarMembroCombo.setItems(FXCollections.observableArrayList(BaseDeDados.getPossiveisMembros()));
        seleccionarMembroCombo.setConverter(new StringConverter<MembroHumano>() {
            @Override
            public String toString(MembroHumano mh) {
                return mh.getNome();
            }

            @Override
            public MembroHumano fromString(String string) {
                return null;
            }
        });
        seleccionarMembroCombo.valueProperty().addListener((obs, oldItem, newItem) -> {
            Sessao.membroHumano = newItem;
        });
    }

    @FXML
    private void verDados(MouseEvent event) throws IOException {
        /*App.novaJanela("fxml/registo/VerFamiliaUI");*/
    }

    @FXML
    private void fecharPrograma(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void minimizarPrograma(MouseEvent event) {
        App.stage.setIconified(true);
    }

    @FXML
    private void novoMembro(MouseEvent event) throws IOException {
        Sessao.acao = "FROM_ESCOLHER_MEMBRO";
        App.novaJanela("fxml/registo/NovoMembroUI");
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void porNaFamilia(MouseEvent event) throws IOException {
        if (seleccionarMembroCombo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Precisa de seleccionar um membro!");
            alert.showAndWait();
        } else if (Sessao.utilizador.eAdmin()) {
            Sessao.acao = "FROM_ESCOLHER_MEMBRO";
            App.novaJanela("fxml/dashboard/admin/EscolherFamiliaUI");
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aviso");
            alert.setHeaderText("Utilizador inserido na família com sucesso!");
            alert.showAndWait();
            Sessao.membroHumano.setFamilia(Sessao.familia);
            Sessao.membroHumano.persistir();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

}
