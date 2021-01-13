package com.lucheses.mmmd.ui.dashboard;

import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.Sessao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lucheses
 */
public class AnimaisUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private Label listaAnimaisLbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!Sessao.membroHumano.eResponsavel()) {
            listaAnimaisLbl.setText("Esta família não tem animais!");
        }
        
    }    

    @FXML
    private void novoAnimal(MouseEvent event) throws IOException {
        App.novaJanela("fxml/dashboard/animais/NovoAnimalUI");
    }
    
}
