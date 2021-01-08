package com.lucheses.mmmd.conf;

import com.lucheses.mmmd.App;
import com.lucheses.mmmd.entidades.Utilizador;
import java.io.IOException;
import javafx.scene.Node;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

    public static void persistir(Object o) {
        EM.getTransaction().begin();
        EM.persist(o);
        EM.getTransaction().commit();
    }

    public static Query novoQuery(String sql) {
        return EM.createQuery(sql);
    }

    public static Utilizador getUtilizadorByEmail(String email) {
        String sql = "SELECT u FROM Utilizador u WHERE u.email = :email";
        TypedQuery<Utilizador> query = EM.createQuery(sql , Utilizador.class);
        return query.setParameter("email", email).getSingleResult();
    }

    public static boolean emailJaExiste(String email) {
        String sql = "SELECT COUNT(*) FROM Utilizador u WHERE u.email = :email";
        TypedQuery<Long> q = EM.createQuery(sql, Long.class);
        return q.setParameter("email", email).getSingleResult() > 0;
    }
}
