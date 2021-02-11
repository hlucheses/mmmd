package com.lucheses.mmmd.ui.dashboard.economia;

import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.Gasto;
import com.lucheses.mmmd.entidades.Rendimento;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lucheses
 */
public class VerRendimentosUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private TableView<Rendimento> rendimentosTbl;
    @FXML
    private TableColumn<Gasto, String> origemCol;
    @FXML
    private TableColumn<Gasto, Double> valorCol;
    @FXML
    private TableColumn<Gasto, Date> dataRendimentoCol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.tornarArrastavel(contentArea);
      
        /*ObservableList<Rendimento> rendimentos = FXCollections.observableArrayList(
                BaseDeDados.getRendimentosFamilia(Sessao.membroHumano.getFamilia()));
        
        origemCol.setCellValueFactory(new PropertyValueFactory<>("origem"));
        valorCol.setCellValueFactory(new PropertyValueFactory<>("valor"));
        dataRendimentoCol.setCellValueFactory(new PropertyValueFactory<>("dataRendimento"));
        rendimentosTbl.setItems(rendimentos);*/
    }    

    @FXML
    private void fecharPrograma(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
}
