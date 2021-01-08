package com.lucheses.mmmd.entidades;

import com.lucheses.mmmd.conf.BaseDeDados;
import java.io.Serializable;

/**
 *
 * @author lucheses
 */
abstract class Entidade implements Serializable {
    
    public void persistir() {
        BaseDeDados.persistir(this);
    }
}
