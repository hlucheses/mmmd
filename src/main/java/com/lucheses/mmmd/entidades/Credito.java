package com.lucheses.mmmd.entidades;

import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author lucheses
 */
@Entity
@Table(name = "credito")
public class Credito extends Gasto {

    @Column(name = "valorPago")
    private double valorPago;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_inicio")
    private Date dataDeInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_fim")
    private Date dataDeFim;

    @Column(name = "valorTotal")
    private double valorTotal;

    public Credito() {

    }

    public double valorMensal() {
        LocalDate ini = Instant.ofEpochMilli(this.dataDeInicio
                .getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fim = Instant.ofEpochMilli(this.dataDeFim
                .getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        long monthsBetween = ChronoUnit.MONTHS.between(YearMonth.from(ini), YearMonth.from(fim));
        if (monthsBetween <= 1) {
            monthsBetween = 1;
        }
        return (double) valorTotal / monthsBetween;
    }
    
    public void incrementaValorPago(double x) {
        this.valorPago += x;
    }

    public Credito(String localGasto, String designacao, double valorGasto, Date dataFim, PrevisaoMensal pm, MembroHumano autorGasto) {
        this.valorPago = 0;
        this.valorTotal = valorGasto;
        this.dataDeFim = dataFim;
        this.previsaoMensal = pm;
        this.localGasto = localGasto;
        this.designacao = designacao;
        this.valor = valorGasto;
        this.autorGasto = autorGasto;
        this.dataGasto = Date.from(LocalDate.now().
                atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
