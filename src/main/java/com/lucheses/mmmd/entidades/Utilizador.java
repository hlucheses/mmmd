package com.lucheses.mmmd.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author lucheses
 */

@Entity
@Table(name = "utilizador")
public class Utilizador {

    @Id
    private String email;
    
    private String password;
    private boolean isset;

    public Utilizador() {

    }

    public void novoUtiliador(String email, String password) {
        this.email = email.toLowerCase();
        this.password = encriptarPassword_SHA_512(password);
        this.isset = false;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
