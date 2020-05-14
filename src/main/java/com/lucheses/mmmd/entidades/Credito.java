package com.lucheses.mmmd.entidades;

import java.util.Date;

/**
 *
 * @author lucheses
 */
public class Credito extends Gasto {
    
    private float valorPago;
    private float valorEmDivida;
    private Date dataDeInicio;
    private Date dataDeFim;
    private float valorTotal;
    
    public Credito() {
        
    }
}