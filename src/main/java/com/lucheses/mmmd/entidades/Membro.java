package com.lucheses.mmmd.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author lucheses
 */

/*@Entity
@Table(name = "membro", uniqueConstraints = {
    @UniqueConstraint(columnNames = "idmembro"),
    @UniqueConstraint(columnNames = "telefone")})*/
public class Membro /*extends Utilizador*/ {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmembro")
    private long id;
    
    @Column(name = "nome")
    private String nome;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nasc")
    private Date dataDeNascimento;
    
    @Column(name = "sexo")
    private char sexo;
    
    @Column(name = "titulo")
    private String titulo;
    
    @Column(name = "telefone")
    private String telefone;
    
    @ManyToOne
    @JoinColumn(name = "idfamilia")
    private Familia familia;

    public Membro() {
        
    }*/
}