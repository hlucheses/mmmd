package com.lucheses.mmmd.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author lucheses
 */
@Entity
@Table(name = "utilizador", uniqueConstraints = {
    @UniqueConstraint(columnNames = "idutilizador"),
    @UniqueConstraint(columnNames = "email")})
public class Utilizador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idutilizador")
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "isset", nullable = false, columnDefinition = "boolean default false")
    private boolean isset;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao")
    private Date data_criacao;

    public Utilizador() {
    }

    public Utilizador(String email, String password) {
        this.email = email.toLowerCase();
        this.password = encriptarPassword_SHA_512(password);
    }

    private String encriptarPassword_SHA_512(String password) {

        String passwordEncriptada = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            passwordEncriptada = no.toString(16);

            while (passwordEncriptada.length() < 32) {
                passwordEncriptada = "0" + passwordEncriptada;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return passwordEncriptada;
    }
}
