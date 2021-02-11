package com.lucheses.mmmd.ui.dashboard.economia;

import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.Gasto;
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
public class VerGastosUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private TableView<Gasto> gastosTbl;
    @FXML
    private TableColumn<Gasto, String> designacaoCol;
    @FXML
    private TableColumn<Gasto, Double> valorCol;
    @FXML
    private TableColumn<Gasto, String> localGastoCol;
    @FXML
    private TableColumn<Gasto, Date> dataGastoCol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.tornarArrastavel(contentArea);
      
        ObservableList<Gasto> gastos = FXCollections.observableArrayList(
                BaseDeDados.getGastosFamilia(Sessao.membroHumano.getFamilia()));
        
        designacaoCol.setCellValueFactory(new PropertyValueFactory<>("designacao"));
        valorCol.setCellValueFactory(new PropertyValueFactory<>("valor"));
        localGastoCol.setCellValueFactory(new PropertyValueFactory<>("localGasto"));
        dataGastoCol.setCellValueFactory(new PropertyValueFactory<>("dataGasto"));
        gastosTbl.setItems(gastos);
    }    

    @FXML
    private void fecharPrograma(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
}
