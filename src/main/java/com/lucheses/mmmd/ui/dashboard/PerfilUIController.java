package com.lucheses.mmmd.ui.dashboard;

import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.Sessao;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author lucheses
 */
public class PerfilUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private Label nomeDirLbl;
    @FXML
    private Label nomeLbl;
    @FXML
    private Label emailLbl;
    @FXML
    private Label telefoneLbl;
    @FXML
    private Label sexoLbl;
    @FXML
    private Pane telefonePane;
    @FXML
    private Label dataDeNascimentoLbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Date d = Sessao.membroHumano.getDataDeNascimento();
        String dataDeNascimento = d.toString();
        
        if (Sessao.membroHumano.getTelefone() != null) {
            telefoneLbl.setText(Sessao.membroHumano.getTelefone());
        } else {
            telefonePane.setVisible(false);
        }
        
        nomeDirLbl.setText(Sessao.membroHumano.getNome());
        nomeLbl.setText(Sessao.membroHumano.getNome());
        emailLbl.setText(Sessao.membroHumano.getEmail());
        dataDeNascimentoLbl.setText(dataDeNascimento);
        sexoLbl.setText(Sessao.membroHumano.getSexo() == 'M' ? "Masculino" : "Feminino");
    }    

    @FXML
    private void alterarDadosUser(MouseEvent event) throws IOException {
        App.novaJanela("fxml/dashboard/perfil/AlterarDadosUserUI");
    }
}
