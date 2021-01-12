package com.lucheses.mmmd.ui.registo;

import com.lucheses.mmmd.App;
import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Sessao;
import com.lucheses.mmmd.entidades.MembroHumano;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lucheses
 */
public class VerFamiliaUIController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private Label enderecoLbl;
    @FXML
    private Label nomeLbl;
    @FXML
    private Label telefoneLbl;
    @FXML
    private Label membrosResponsaveisLbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.tornarArrastavel(contentArea);
        String enderecoEBairro = Sessao.familia.getEndereco() + "; " + Sessao.familia.getBairro();
        List<MembroHumano> membrosResponsaveis = BaseDeDados.getMembrosResponsaveis(Sessao.familia);
        String strMembrosResponsaveis = "";
        for (MembroHumano membroResponsavel : membrosResponsaveis) {
            strMembrosResponsaveis += membroResponsavel.getNome() + "; ";
        }
        
        nomeLbl.setText(Sessao.familia.getNome());
        telefoneLbl.setText(Sessao.familia.getTelefone());
        enderecoLbl.setText(enderecoEBairro);
        membrosResponsaveisLbl.setText(strMembrosResponsaveis);
        
    }  
    
    @FXML
    private void fecharPrograma(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
}
