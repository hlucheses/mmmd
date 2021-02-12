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
public class VerRendimentosUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private TableView<Rendimento> rendimentosTbl;
    @FXML
    private TableColumn<Rendimento, String> origemCol;
    @FXML
    private TableColumn<Rendimento, Double> valorCol;
    @FXML
    private TableColumn<Rendimento, Date> dataRendimentoCol;
    @FXML
    private TableColumn<Rendimento, String> autorRendimentoCol;
    @FXML
    private TableColumn<Rendimento, Void> actualizarCol;
    @FXML
    private TableColumn<Rendimento, Void> eliminarCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.tornarArrastavel(contentArea);

        if (!Sessao.membroHumano.eResponsavel()) {
            eliminarCol.setVisible(false);
        }

        ObservableList<Rendimento> rendimentos = FXCollections.observableArrayList(
                BaseDeDados.getRendimentosFamilia(Sessao.membroHumano.getFamilia()));

        origemCol.setCellValueFactory(new PropertyValueFactory<>("origem"));
        valorCol.setCellValueFactory(new PropertyValueFactory<>("valor"));
        dataRendimentoCol.setCellValueFactory(new PropertyValueFactory<>("dataRendimento"));
        autorRendimentoCol.setCellValueFactory(new PropertyValueFactory<>("nomeAutorRendimento"));
        rendimentosTbl.setItems(rendimentos);
        adicionarBotoes();
    }

    private void adicionarBotoes() {

        Callback<TableColumn<Rendimento, Void>, TableCell<Rendimento, Void>> cfEliminar
                = new Callback<TableColumn<Rendimento, Void>, TableCell<Rendimento, Void>>() {
            @Override
            public TableCell<Rendimento, Void> call(final TableColumn<Rendimento, Void> param) {
                final TableCell<Rendimento, Void> cell = new TableCell<Rendimento, Void>() {

                    private final JFXButton btn = new JFXButton("Eliminar");

                    {
                        btn.getStyleClass().add("loginBtn");
                        btn.setOnAction((ActionEvent event) -> {
                            Sessao.rendimento = getTableView().getItems().get(getIndex());
                            Sessao.rendimento.remover();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Informação");
                            alert.setHeaderText("Rendimento eliminado com sucesso!");
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

        Callback<TableColumn<Rendimento, Void>, TableCell<Rendimento, Void>> cfActualizar
                = new Callback<TableColumn<Rendimento, Void>, TableCell<Rendimento, Void>>() {
            @Override
            public TableCell<Rendimento, Void> call(final TableColumn<Rendimento, Void> param) {
                final TableCell<Rendimento, Void> cell = new TableCell<Rendimento, Void>() {

                    private final JFXButton btn = new JFXButton("Actualizar");

                    {
                        btn.getStyleClass().add("loginBtn");
                        btn.setOnAction((ActionEvent event) -> {
                            Sessao.rendimento = getTableView().getItems().get(getIndex());
                            try {
                                App.novaJanela("fxml/dashboard/economia/EditarRendimentoUI");
                            } catch (IOException ex) {
                                Logger.getLogger(VerRendimentosUIController.class.getName()).log(Level.SEVERE, null, ex);
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
