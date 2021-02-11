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

/**
 *
 * @author lucheses
 */

@Entity
@Table(name = "rendimento")
public class Rendimento extends Entidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrendimento")
    private long id;
    
    @Column(name = "valor")
    private double valor;
    
    @Column(name = "origem")
    private String origem;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_rendimento")
    private Date dataRendimento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previsao_mensal_idprevisao_mensal", referencedColumnName = "idprevisao_mensal")
    protected PrevisaoMensal previsaoMensal;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membro_humano_idmembro", referencedColumnName = "idmembro")
    protected MembroHumano beneficiarioRendimento;
    
    public Rendimento() {
        
    }
    
    public double getValor() {
        return this.valor;
    }
    
    public String getOrigem() {
        return this.origem;
    }
    
    public Date getDataRendimento() {
        return this.dataRendimento;
    }
    
    public Rendimento(Date dataRendimento, double valor, String origem, MembroHumano beneficiarioRendimento, PrevisaoMensal previsaoMensal) {
        this.dataRendimento = dataRendimento;
        this.valor = valor;
        this.origem = origem;
        this.beneficiarioRendimento = beneficiarioRendimento;
        this.previsaoMensal = previsaoMensal;
    }
}