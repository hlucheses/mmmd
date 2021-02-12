package com.lucheses.mmmd.conf;

import java.io.Serializable;

/**
 *
 * @author lucheses
 */
public abstract class Entidade implements Serializable {
    
    public void persistir() {
        BaseDeDados.persistir(this);
    }
    
    public void remover() {
        BaseDeDados.remover(this);
    }
    
    public void comitar() {
        BaseDeDados.comitar();
    }
}
