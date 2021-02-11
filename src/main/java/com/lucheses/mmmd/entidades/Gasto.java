package com.lucheses.mmmd.entidades;

import com.lucheses.mmmd.conf.Entidade;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "gasto")
public class Gasto extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgasto")
    protected long id;
    
    @Column(name = "designacao")
    protected String designacao;
    
    @Column(name = "valor")
    protected double valor;
    
    @Column(name = "local_gasto")
    protected String localGasto;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_gasto")
    protected Date dataGasto;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previsao_mensal_idprevisao_mensal", referencedColumnName = "idprevisao_mensal")
    protected PrevisaoMensal previsaoMensal;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membro_idmembro", referencedColumnName = "idmembro")
    protected MembroHumano autorGasto;

    public Gasto() {

    }

    public Gasto(Date dataGasto, double valor, String designacao, String local, MembroHumano autorGasto, PrevisaoMensal previsaoMensal) {
        this.dataGasto = dataGasto;
        this.valor =  valor;
        this.designacao = designacao;
        this.localGasto = local;
        this.autorGasto = autorGasto;
        this.previsaoMensal = previsaoMensal;
    }
}
