/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucheses.mmmd.conf;

import com.lucheses.mmmd.entidades.Familia;
import com.lucheses.mmmd.entidades.Membro;
import com.lucheses.mmmd.entidades.MembroHumano;
import com.lucheses.mmmd.entidades.Utilizador;

/**
 *
 * @author lucheses
 */
public class Sessao {
    public static Utilizador utilizador;
    public static MembroHumano membroHumano;
    public static Membro membro;
    public static Familia familia;
    
    static {
        utilizador = null;
        membroHumano = null;
        familia = null;
    }
    
    private Sessao() {
    }
}