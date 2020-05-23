/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucheses.mmmd.conf;

import com.lucheses.mmmd.entidades.Utilizador;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author lucheses
 */
public final class BaseDeDados {

    private static final EntityManager EM;
    private static final EntityManagerFactory EMF;

    static {
        EMF = Persistence.createEntityManagerFactory("MyMoneyMyDecisionPU");
        EM = EMF.createEntityManager();
    }

    private BaseDeDados() {
    }

    public static void novoUtilizador(Utilizador utilizador) {
        EM.getTransaction().begin();
        EM.persist(utilizador);
        EM.getTransaction().commit();
    }

    public static boolean emailJaExiste(String email) {
        String sql = "SELECT COUNT(*) FROM Utilizador u WHERE u.email = :email";
        TypedQuery<Long> q = EM.createQuery(sql, Long.class);
        return q.setParameter("email", email).getSingleResult() > 0;
    }
}
