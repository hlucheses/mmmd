package com.lucheses.mmmd.entidades;

import com.lucheses.mmmd.conf.Entidade;
import com.lucheses.mmmd.conf.BaseDeDados;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author lucheses
 */
@Entity
@Table(name = "utilizador")
public class Utilizador extends Entidade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idutilizador")
    private long id;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "isset", nullable = false, columnDefinition = "boolean default false")
    private boolean set;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao")
    private Date data_criacao;
    
    @OneToOne(mappedBy = "utilizador")
    private MembroHumano membroHumano;
    
    public Utilizador() {
    }
    
    public Utilizador(String username, String password) {
        this.username = username.toLowerCase();
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
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }
    
    public void setPassword(String password) {
        this.password = encriptarPassword_SHA_512(password);
    }
    
    public boolean isSet() {
        return this.set;
    }
    
    public void setSet(boolean b) {
        this.set = b;
    }
    
    public boolean eAdmin() {
        return this.getUsername().equals("admin");
    }
    
    public MembroHumano getMembroHumano() {
        return this.membroHumano;
    }
    
    public boolean verificarCredenciais() {
        
        if (BaseDeDados.usernameJaExiste(this.username)) {
            Utilizador u = BaseDeDados.getUtilizadorByUsername(this.username);
            if (u.password.equals(this.password)) {
                return true;
            }
        }
        
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText("Ocorreu um erro ao tentar fazer login!");
        alert.setContentText("Email ou password incorrectos!");
        alert.showAndWait();
        
        return false;
    }
    
    public static boolean validarPassword(String password, String confirmPassword) {
        final Pattern PASSWORD_VALIDA_TAMANHO = Pattern.compile("^.{8,16}$");
        final Pattern PASSWORD_VALIDA_CARACTERES
                = Pattern.compile("^(?=.*?[0-9])(?=.*?[A-Za-z]).+$");
        Matcher matcherTam = PASSWORD_VALIDA_TAMANHO.matcher(password);
        Matcher matcherChar = PASSWORD_VALIDA_CARACTERES.matcher(password);

        boolean passwordTamVal = matcherTam.find();
        boolean passwordCharVal = matcherChar.find();
        boolean passwordEqualsConfirm = password.equals(confirmPassword);
        boolean passwordValida = passwordTamVal && passwordCharVal && passwordEqualsConfirm;

        if (!passwordValida) {

            String strPwErro = "";

            if (!passwordTamVal) {
                strPwErro += "A password deve ter de 8 a 16 caracteres!\n";
            }

            if (!passwordCharVal) {
                strPwErro += "A password deve ter letras e números!\n";
            }

            if (!passwordEqualsConfirm) {
                strPwErro += "As passwords não correspondem!\n";
            }

            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Ocorreu um erro ao tentar criar uma conta!");
            alert.setContentText("Password inválida!\n" + strPwErro);
            alert.showAndWait();
        }
        return passwordValida;
    }
}
