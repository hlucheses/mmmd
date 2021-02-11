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
public class EscolherMembroResponsavelUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private JFXComboBox<MembroHumano> seleccionarMembroCombo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.tornarArrastavel(contentArea);
        seleccionarMembroCombo.setItems(FXCollections.observableArrayList(BaseDeDados.getPossiveisResponsaveis()));
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
    private void voltar(MouseEvent event) throws IOException {
        App.novaJanela("fxml/dashboard/admin/NovaFamiliaUI");
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void verDados(MouseEvent event) throws IOException {
        /*App.novaJanela("fxml/registo/VerFamiliaUI");*/
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
    private void porNaFamilia(MouseEvent event) throws IOException {
        Sessao.familia.persistir();
        Sessao.membroHumano.setResponsavel(true);
        Sessao.membroHumano.setFamilia(Sessao.familia);
        Sessao.membroHumano.persistir();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("INFORMATION");
        alert.setHeaderText("Familia criada com sucesso!");
        alert.showAndWait();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

}
