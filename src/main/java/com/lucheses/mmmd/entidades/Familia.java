package com.lucheses.mmmd.entidades;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author lucheses
 */
@Entity
@Table(name = "familia")
public class Familia extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfamilia")
    private long id;

    @Column(name = "nome")
    private String nome;
    
    @Column(name = "endereco")
    private String endereco;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "telefone")
    private String telefoneDeCasa;

    @OneToMany(mappedBy = "familia", fetch = FetchType.LAZY)
    private List<Membro> membros;

    public Familia() {

    }
    
    public Familia(String nome) {
        this.nome = nome;
    }

    public Familia(String nomeDaFamilia, String telefoneDeCasa, String endereco, String bairro) {
        this.nome = nomeDaFamilia;
        this.telefoneDeCasa = telefoneDeCasa;
        this.endereco = endereco;
        this.bairro = bairro;       
    }

    public boolean estaDefinida() {
        return this != null;
    }
    
    public String getNome() {
        return this.nome;
    }
}
