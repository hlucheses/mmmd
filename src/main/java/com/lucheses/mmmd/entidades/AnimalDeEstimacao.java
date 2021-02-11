package com.lucheses.mmmd.entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author lucheses
 */

@Entity
@Table(name = "animal")
public class AnimalDeEstimacao extends Membro {

    @Column(name = "especie")
    private String especie;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_aquisicao")
    private Date dataDeAquisicao;
    
    @Column(name = "raca")
    private String raca;
    
    public AnimalDeEstimacao() {
        
    }

    public AnimalDeEstimacao(String nome, String especie, String raca, Date dataDeNascimento, Date dataDeAquisicao, char sexo, Familia familia) {
        this.nome = nome;
        this.dataDeAquisicao = dataDeAquisicao;
        this.especie = especie;
        this.raca = raca;
        this.sexo = sexo;
        this.dataDeNascimento = dataDeNascimento;
        this.familia = familia;
    }
}