package com.lucheses.mmmd.ui.dashboard;

import com.jfoenix.controls.JFXButton;
import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.MembroHumano;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
public class FamiliaUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private Label enderecoLbl;
    @FXML
    private Label nomeLbl;
    @FXML
    private Label nomeDirLbl;
    @FXML
    private Label telefoneLbl;
    @FXML
    private Label membrosResponsaveisLbl;
    @FXML
    private JFXButton novoMembroBtn;
    @FXML
    private JFXButton alterarDadosBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        App.tornarArrastavel(contentArea);
        
        String enderecoEBairro = Sessao.familia.getEndereco() + "; " + Sessao.familia.getBairro();
        List<MembroHumano> membrosResponsaveis = BaseDeDados.getMembrosResponsaveis(Sessao.familia);
        String strMembrosResponsaveis = "";
        for (MembroHumano membroResponsavel : membrosResponsaveis) {
            strMembrosResponsaveis += membroResponsavel.getNome() + "; ";
        }
        
        nomeDirLbl.setText(Sessao.familia.getNome());
        nomeLbl.setText(Sessao.familia.getNome());
        telefoneLbl.setText(Sessao.familia.getTelefone());
        enderecoLbl.setText(enderecoEBairro);
        membrosResponsaveisLbl.setText(strMembrosResponsaveis);
        
        if (!Sessao.membroHumano.eResponsavel()) {
            novoMembroBtn.setVisible(false);
        alterarDadosBtn.setVisible(false);
        }
        
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
