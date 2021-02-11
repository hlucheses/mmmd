package com.lucheses.mmmd.entidades;

import com.lucheses.mmmd.conf.BaseDeDados;
import com.lucheses.mmmd.conf.Entidade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author lucheses
 */
@Entity
@Table(name = "previsao_mensal")
public class PrevisaoMensal extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprevisao_mensal")
    private long id;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_previsao")
    private Date dataPrevisao;
    
    @Column(name = "poupanca")
    private double poupanca;
     
    @OneToMany(mappedBy = "previsaoMensal", fetch = FetchType.LAZY)
    private List<Rendimento> rendimentoMensal;
    
    @OneToMany(mappedBy = "previsaoMensal", fetch = FetchType.LAZY)
    private List<Gasto> gastoMensal;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familia_idfamilia", referencedColumnName = "idfamilia")
    protected Familia familia;

    public PrevisaoMensal() {
    }
    
    public Date getDataPrevisao() {
        return this.dataPrevisao;
    }
    
    public double getPoupanca() {
        return this.poupanca;
    }
    
    public PrevisaoMensal(double poupanca, Date dataPrevisao, Familia familia) {
        this.poupanca = poupanca;
        this.dataPrevisao = dataPrevisao;
        this.familia = familia;
    }
    
    public static Date dataPrevisao(Familia f) {
        return BaseDeDados.getUltimaPrevisao(f);
    }
    
    public double calcularRendimentos() {
        double rendimentos = 0;
        for (int i = 0; i < this.rendimentoMensal.size(); i++) {
            rendimentos += this.rendimentoMensal.get(i).getValor();
        }
        System.out.println(rendimentos);
        return rendimentos;
    }
    
    public double calcularGastos() {
        double gastos = 0;
        for (int i = 0; i < this.gastoMensal.size(); i++) {
            gastos += this.gastoMensal.get(i).getValor();
        }
        System.out.println(gastos);
        return gastos;
    }
}