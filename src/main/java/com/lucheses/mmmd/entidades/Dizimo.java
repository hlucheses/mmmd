package com.lucheses.mmmd.entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author lucheses
 */
@Entity
@Table(name = "dizimo")
public class Dizimo extends Gasto {

    @Column(name = "beneficiario")
    private String instituicaoBenificiaria;

    public Dizimo() {

    }

    public Dizimo(Date dataGasto, MembroHumano autorGasto, String instituicao, PrevisaoMensal previsaoMensal) {
        this.dataGasto = dataGasto;
        this.valor = previsaoMensal.calcularRendimentos() * .1;
        this.designacao = "Dízimo";
        this.localGasto = instituicao;
        this.instituicaoBenificiaria = instituicao;
        this.autorGasto = autorGasto;
        this.previsaoMensal = previsaoMensal;
    }
}
