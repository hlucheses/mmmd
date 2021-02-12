package com.lucheses.mmmd.ui.dashboard;

import com.jfoenix.controls.JFXButton;
import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author lucheses
 */
public class EconomiaUIController implements Initializable {

    @FXML
    JFXButton novoGasto;
    @FXML
    JFXButton novoRendimento;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void novoGasto(MouseEvent event) throws IOException {
        if (!BaseDeDados.haPrevisoesMensais(Sessao.membroHumano.getFamilia())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Sem previsões!");
            alert.setContentText("Impossível haver gastos ou rendimentos sem previ-"
                    + "\nsões mensais");
            alert.showAndWait();
        } else {
            App.novaJanela("fxml/dashboard/economia/NovoGastoUI");
        }
    }

    @FXML
    private void novoRendimento(MouseEvent event) throws IOException {
        if (!BaseDeDados.haPrevisoesMensais(Sessao.membroHumano.getFamilia())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Sem previsões!");
            alert.setContentText("Impossível haver gastos ou rendimentos sem previ-"
                    + "\nsões mensais");
            alert.showAndWait();
        } else {
            App.novaJanela("fxml/dashboard/economia/NovoRendimentoUI");
        }

    }

    @FXML
    private void novaPrevisao(MouseEvent event) throws IOException {
        App.novaJanela("fxml/dashboard/economia/NovaPrevisaoUI");
    }

    @FXML
    private void verPrevisoes(MouseEvent event) throws IOException {
        BaseDeDados.reiniciar();
        if (!BaseDeDados.haPrevisoesMensais(Sessao.membroHumano.getFamilia())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setHeaderText("Sem previsões!");
            alert.showAndWait();
        } else {
            App.novaJanela("fxml/dashboard/economia/VerPrevisoesUI");
        }
    }

    @FXML
    private void verGastos(MouseEvent event) throws IOException {
        App.novaJanela("fxml/dashboard/economia/VerGastosUI");
    }

    @FXML
    private void verRendimentos(MouseEvent event) throws IOException {
        App.novaJanela("fxml/dashboard/economia/VerRendimentosUI");
    }
    
    @FXML
    private void novoCredito(MouseEvent event) throws IOException {
        if (BaseDeDados.todosOsCreditosPagos(Sessao.membroHumano.getFamilia())) {
            App.novaJanela("fxml/dashboard/economia/NovoCreditoUI");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ayo");
            alert.setHeaderText("Há créditos por pagar");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void verCreditos(MouseEvent event) throws IOException {
        
        App.novaJanela("fxml/dashboard/economia/VerCreditosUI");
    }

}
