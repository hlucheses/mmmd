package com.lucheses.mmmd.conf;

import com.lucheses.mmmd.entidades.Familia;
import com.lucheses.mmmd.entidades.MembroHumano;
import com.lucheses.mmmd.entidades.Utilizador;

/**
 *
 * @author lucheses
 */
public class Sessao {
    public static Utilizador utilizador;
    public static MembroHumano membroHumano;
    public static Familia familia;
    
    static {
        utilizador = null;
        membroHumano = null;
        familia = null;
    }
    
    public static boolean estaDefinido(Object o) {
        return o != null;
    }
    
    private Sessao() {
    }
}