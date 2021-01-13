package com.lucheses.mmmd.conf;

import com.lucheses.mmmd.entidades.Familia;
import com.lucheses.mmmd.entidades.MembroHumano;
import com.lucheses.mmmd.entidades.Utilizador;
import java.util.List;
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

    public static void comitar() {
        EM.getTransaction().begin();
        EM.getTransaction().commit();
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
    
    public static boolean haFamilias() {
        String sql = "SELECT COUNT(*) FROM Familia f";
        TypedQuery<Long> q = EM.createQuery(sql, Long.class);
        return q.getSingleResult() > 0;
    }
    
    public static boolean haAnimais(Familia f) {
        String sql = "SELECT COUNT(*) FROM AnimalDeEstimacao a INNER JOIN Membro m ON a.id = m.id WHERE m.familia = :familia";
        TypedQuery<Long> q = EM.createQuery(sql, Long.class);
        return q.setParameter("familia", f).getSingleResult() > 0;
    }
    
    public static List<Familia> getTodasAsFamilias() {
        String sql = "SELECT f FROM Familia f ORDER BY f.nome";
        TypedQuery<Familia> query = EM.createQuery(sql , Familia.class);
        return query.getResultList();
    }
    
    public static List<MembroHumano> getMembrosResponsaveis(Familia f) {
        String sql = "SELECT mh FROM MembroHumano mh INNER JOIN Membro m ON m.id = mh.id WHERE m.familia = :familia AND mh.responsavel = true";
        TypedQuery<MembroHumano> query = EM.createQuery(sql , MembroHumano.class);
        return query.setParameter("familia", f).getResultList();
    }
}
