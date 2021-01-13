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
}