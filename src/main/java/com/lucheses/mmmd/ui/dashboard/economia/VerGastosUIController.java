package com.lucheses.mmmd.ui.dashboard.economia;

import com.jfoenix.controls.JFXButton;
import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.Gasto;
import com.lucheses.mmmd.entidades.Rendimento;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

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
    @FXML
    private TableColumn<Gasto, String> autorGastoCol;
    @FXML
    private TableColumn<Gasto, Void> actualizarCol;
    @FXML
    private TableColumn<Gasto, Void> eliminarCol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.tornarArrastavel(contentArea);
      
        if (!Sessao.membroHumano.eResponsavel()) {
            actualizarCol.setVisible(false);
            eliminarCol.setVisible(false);
        }
        
        ObservableList<Gasto> gastos = FXCollections.observableArrayList(
                BaseDeDados.getGastosFamilia(Sessao.membroHumano.getFamilia()));
        
        designacaoCol.setCellValueFactory(new PropertyValueFactory<>("designacao"));
        valorCol.setCellValueFactory(new PropertyValueFactory<>("valor"));
        localGastoCol.setCellValueFactory(new PropertyValueFactory<>("localGasto"));
        dataGastoCol.setCellValueFactory(new PropertyValueFactory<>("dataGasto"));
        autorGastoCol.setCellValueFactory(new PropertyValueFactory<>("nomeAutorGasto"));
        gastosTbl.setItems(gastos);
        adicionarBotoes();
    }    

    private void adicionarBotoes() {

        Callback<TableColumn<Gasto, Void>, TableCell<Gasto, Void>> cfEliminar
                = new Callback<TableColumn<Gasto, Void>, TableCell<Gasto, Void>>() {
            @Override
            public TableCell<Gasto, Void> call(final TableColumn<Gasto, Void> param) {
                final TableCell<Gasto, Void> cell = new TableCell<Gasto, Void>() {

                    private final JFXButton btn = new JFXButton("Eliminar");

                    {
                        btn.getStyleClass().add("loginBtn");
                        btn.setOnAction((ActionEvent event) -> {
                            Sessao.gasto = getTableView().getItems().get(getIndex());
                            Sessao.gasto.remover();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Informação");
                            alert.setHeaderText("Gasto eliminado com sucesso!");
                            alert.showAndWait();
                            ((Node) (event.getSource())).getScene().getWindow().hide();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        eliminarCol.setCellFactory(cfEliminar);

        Callback<TableColumn<Gasto, Void>, TableCell<Gasto, Void>> cfActualizar
                = new Callback<TableColumn<Gasto, Void>, TableCell<Gasto, Void>>() {
            @Override
            public TableCell<Gasto, Void> call(final TableColumn<Gasto, Void> param) {
                final TableCell<Gasto, Void> cell = new TableCell<Gasto, Void>() {

                    private final JFXButton btn = new JFXButton("Actualizar");

                    {
                        btn.getStyleClass().add("loginBtn");
                        btn.setOnAction((ActionEvent event) -> {
                            Sessao.gasto = getTableView().getItems().get(getIndex());
                            try {
                                App.novaJanela("fxml/dashboard/economia/EditarGastoUI");
                            } catch (IOException ex) {
                                Logger.getLogger(VerGastosUIController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ((Node) (event.getSource())).getScene().getWindow().hide();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        actualizarCol.setCellFactory(cfActualizar);
    }
    
    @FXML
    private void fecharPrograma(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
}
