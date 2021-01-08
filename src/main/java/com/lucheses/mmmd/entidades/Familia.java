package com.lucheses.mmmd.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author lucheses
 */
@Entity
@Table(name = "familia", uniqueConstraints = {
    @UniqueConstraint(columnNames = "idfamilia"),
    @UniqueConstraint(columnNames = "telefone")})
public class Familia extends Entidade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfamilia")
    private long id;
    
    @Column(name = "endereco")
    private String endereco;
    
    @Column(name = "bairro")
    private String bairro;
    
    @Column(name = "telefone")
    private String telefoneDeCasa;
   
    public Familia() {
        
    }
}