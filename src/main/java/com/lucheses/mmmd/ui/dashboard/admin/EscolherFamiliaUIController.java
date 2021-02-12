package com.lucheses.mmmd.ui.dashboard.admin;

import com.jfoenix.controls.JFXButton;
import com.lucheses.mmmd.ui.registo.*;
import com.jfoenix.controls.JFXComboBox;
import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.Familia;
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
public class EscolherFamiliaUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private JFXComboBox<Familia> seleccionarFamiliaCombo;
    @FXML
    private JFXButton voltarBtn;
    @FXML
    private JFXButton entrarFamiliaBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        App.tornarArrastavel(contentArea);
        
        if (Sessao.acao.equals("VER_FAMILIA_ADMIN")) {
            entrarFamiliaBtn.setVisible(false);
            voltarBtn.setVisible(false);
        }
        
        seleccionarFamiliaCombo.setItems(FXCollections.observableArrayList(BaseDeDados.getTodasAsFamilias()));
        seleccionarFamiliaCombo.setConverter(new StringConverter<Familia>() {
            @Override
            public String toString(Familia f) {
                return f.getNome();
            }

            @Override
            public Familia fromString(String string) {
                return null;
            }
        });
        seleccionarFamiliaCombo.valueProperty().addListener((obs, oldItem, newItem) -> {
            Sessao.familia = newItem;
        });
    }

    @FXML
    private void novaFamilia(MouseEvent event) throws IOException {
        App.novaJanela("fxml/registo/NovaFamiliaUI");
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void voltar(MouseEvent event) throws IOException {
        if (Sessao.acao.equals("FROM_ESCOLHER_MEMBRO")) {
            App.novaJanela("fxml/dashboard/admin/EscolherMembroUI");
            Sessao.acao = "DEFAULT";
        } else {
            App.novaJanela("fxml/registo/NovoMembroUI");
        }
        
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void verDados(MouseEvent event) throws IOException {
        App.novaJanela("fxml/registo/VerFamiliaUI");
    }

    @FXML
    private void fecharPrograma(MouseEvent event) {
        Sessao.acao = "DEFAULT";
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void minimizarPrograma(MouseEvent event) {
        App.stage.setIconified(true);
    }

    @FXML
    private void entrarNaFamilia(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText("Utilizador inserido na família com sucesso!");
        alert.showAndWait();
        Sessao.membroHumano.setFamilia(Sessao.familia);
        Sessao.membroHumano.persistir();
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }

}
