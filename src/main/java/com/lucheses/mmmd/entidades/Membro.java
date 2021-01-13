package com.lucheses.mmmd.entidades;

import com.lucheses.mmmd.conf.Entidade;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author lucheses
 */
@Entity
@Table(name = "membro")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Membro extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmembro")
    protected long id;

    @Column(name = "nome")
    protected String nome;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    protected Date dataDeNascimento;

    @Column(name = "sexo")
    protected char sexo;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao")
    protected Date data_criacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "familia_idfamilia", referencedColumnName = "idfamilia", nullable = false)
    protected Familia familia;
    
    public Familia getFamilia() {
        return this.familia;
    }
    
    public void setFamilia(Familia f) {
        this.familia = f;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
    
    public Date getDataDeNascimento() {
        return this.dataDeNascimento;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    
    public char getSexo() {
        return this.sexo;
    }
}
