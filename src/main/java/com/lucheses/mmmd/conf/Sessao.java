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
        if (!BaseDeDados.usernameJaExiste("admin")) {
            Utilizador admin = new Utilizador("admin", "admin");
            admin.setSet(true);
            admin.persistir();
        }
    }
    
    public static boolean estaDefinido(Object o) {
        return o != null;
    }
    
    public static void terminar() {
        utilizador = null;
        membroHumano = null;
        familia = null;
    }

    public static void iniciar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Sessao() {
    }
}