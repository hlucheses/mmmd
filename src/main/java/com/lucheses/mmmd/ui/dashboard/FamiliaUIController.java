package com.lucheses.mmmd.ui.dashboard;

import com.lucheses.mmmd.App;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lucheses
 */
public class FamiliaUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private FontAwesomeIconView minimizarBtn1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void minimizarPrograma(MouseEvent event) {
    }

    @FXML
    private void alterarDadosFamilia(MouseEvent event) throws IOException {
        App.novaJanela("fxml/dashboard/familia/AlterarDadosFamiliaUI");
    }
    
    @FXML
    private void novoMembro(MouseEvent event) throws IOException {
        App.novaJanela("fxml/dashboard/familia/AdicionarMembroUI");
    }
    
    
}
