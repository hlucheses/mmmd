package com.lucheses.mmmd.conf;

import com.lucheses.mmmd.entidades.Familia;
import com.lucheses.mmmd.entidades.Gasto;
import com.lucheses.mmmd.entidades.MembroHumano;
import com.lucheses.mmmd.entidades.PrevisaoMensal;
import com.lucheses.mmmd.entidades.Rendimento;
import com.lucheses.mmmd.entidades.Utilizador;

/**
 *
 * @author lucheses
 */
public class Sessao {
    public static Utilizador utilizador;
    public static MembroHumano membroHumano;
    public static Familia familia;
    public static Rendimento rendimento;
    public static Gasto gasto;
    public static String acao;
    public static PrevisaoMensal previsaoMensal;
    
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
        rendimento = null;
        gasto = null;
        previsaoMensal = null;
        acao = "DEFAULT";
    }
    
    private Sessao() {
    }
}