/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucheses.mmmd.entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author lucheses
 */
@Entity
@Table(name = "membro_humano")
public class MembroHumano extends Membro {

    @Column(name = "telefone")
    String telefone;

    @Column(name = "responsavel", nullable = false, columnDefinition = "boolean default false")
    private boolean responsavel;

    @OneToOne
    @JoinColumn(name = "utilizador_idutilizador", referencedColumnName = "idutilizador")
    private Utilizador utilizador;

    public MembroHumano() {
    }

    public MembroHumano(String nomeDoMembro, Date dataDeNascimento, char sexo) {
        super(nomeDoMembro, dataDeNascimento, sexo);
    }

    /*public MembroHumano() {
        this.responsavel = false;
    }*/

    /*public MembroHumano(String nome, String telefone, Date dataDeNascimento, char sexo) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataDeNascimento = dataDeNascimento;
        this.sexo = sexo;
        this.responsavel = false;
    }*/

    public void tornarResponsavel() {
        this.responsavel = true;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public void setFamilia(Familia f) {
        this.familia = f;
    }

}
