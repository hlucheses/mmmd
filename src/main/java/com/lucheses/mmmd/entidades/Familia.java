package com.lucheses.mmmd.entidades;

import com.lucheses.mmmd.conf.Entidade;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
        return this.getNome() != null;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public String getTelefone() {
        return this.telefoneDeCasa;
    }
    
    public long getId() {
        return this.id;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEndereco() {
        return this.endereco;
    }
    
    public String getBairro() {
        return this.bairro;
    }
}
