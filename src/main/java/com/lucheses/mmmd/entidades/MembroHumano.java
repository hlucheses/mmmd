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

    @Column(name = "email")
    String email;

    @Column(name = "responsavel", nullable = false, columnDefinition = "boolean default false")
    private boolean responsavel;

    @OneToOne
    @JoinColumn(name = "utilizador_idutilizador", referencedColumnName = "idutilizador")
    private Utilizador utilizador;

    public MembroHumano() {
    }

    public MembroHumano(String nomeDoMembro, Date dataDeNascimento, char sexo, String telefone, String email) {
        this.nome = nomeDoMembro;
        this.dataDeNascimento = dataDeNascimento;
        this.sexo = sexo;
        if (!email.equals("")) {
            this.email = email;
        }
        if (!telefone.equals("")) {
            this.telefone = telefone;
        }
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public boolean eResponsavel() {
        return this.responsavel;
    }

    public void tornarResponsavel() {
        this.setResponsavel(true);
    }

    public Utilizador getUtilizador() {
        return this.utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public void setResponsavel(boolean b) {
        this.responsavel = b;
    }

    public boolean temFamilia() {
        return this.familia != null;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
