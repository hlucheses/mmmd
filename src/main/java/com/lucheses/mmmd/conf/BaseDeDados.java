package com.lucheses.mmmd.conf;

import com.lucheses.mmmd.entidades.Familia;
import com.lucheses.mmmd.entidades.MembroHumano;
import com.lucheses.mmmd.entidades.PrevisaoMensal;
import com.lucheses.mmmd.entidades.Utilizador;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
    
    public static boolean haPrevisoesMensais(Familia f) {
        String sql = "SELECT COUNT(*) FROM PrevisaoMensal pm WHERE pm.familia = :familia";
        TypedQuery<Long> q = EM.createQuery(sql, Long.class);
        return q.setParameter("familia", f).getSingleResult() > 0;
    }
    
    public static PrevisaoMensal buscarUltimaPrevisao(Familia f) {
        String sql = "SELECT pm FROM PrevisaoMensal pm WHERE pm.familia = :familia ORDER BY pm.dataPrevisao DESC";
        TypedQuery<PrevisaoMensal> query = EM.createQuery(sql, PrevisaoMensal.class);
        return query.setParameter("familia", f).getSingleResult();
    }

    public static Date getUltimaPrevisao(Familia f) {
        String sql = "SELECT COUNT(*) FROM PrevisaoMensal pm WHERE pm.familia = :familia";
        TypedQuery<Long> q = EM.createQuery(sql, Long.class);
        if (!(q.setParameter("familia", f).getSingleResult() > 0)) {
            return Date.from(LocalDate.now().
                atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        sql = "SELECT pm FROM PrevisaoMensal pm WHERE pm.familia = :familia ORDER BY pm.dataPrevisao DESC";
        TypedQuery<PrevisaoMensal> query = EM.createQuery(sql, PrevisaoMensal.class);
        PrevisaoMensal pm = query.setParameter("familia", f).getSingleResult();
        return Date.from(pm.getDataPrevisao().toInstant().atZone(ZoneId.
                systemDefault()).toLocalDate().plusMonths(1).atStartOfDay(
                        ZoneId.systemDefault()).toInstant());
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

    public static Utilizador getUtilizadorByUsername(String username) {
        String sql = "SELECT u FROM Utilizador u WHERE u.username = :username";
        TypedQuery<Utilizador> query = EM.createQuery(sql , Utilizador.class);
        return query.setParameter("username", username).getSingleResult();
    }

    public static boolean usernameJaExiste(String username) {
        String sql = "SELECT COUNT(*) FROM Utilizador u WHERE u.username = :username";
        TypedQuery<Long> q = EM.createQuery(sql, Long.class);
        return q.setParameter("username", username).getSingleResult() > 0;
    }
    
    public static boolean haFamilias() {
        String sql = "SELECT COUNT(*) FROM Familia f";
        TypedQuery<Long> q = EM.createQuery(sql, Long.class);
        return q.getSingleResult() > 0;
    }
    
    public static boolean haUtilizadoresDisponiveis() {
        String sql = "SELECT COUNT(*) FROM Membro m WHERE m.familia = null";
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
    
    public static List<MembroHumano> getMembrosFamilia(Familia f) {
        String sql = "SELECT mh FROM MembroHumano mh WHERE mh.familia = :familia ORDER BY mh.nome";
        TypedQuery<MembroHumano> query = EM.createQuery(sql , MembroHumano.class);
        return query.setParameter("familia", f).getResultList();
    }
    
    public static List<MembroHumano> getPossiveisResponsaveis() {
        Date dataMinima = Date.from(LocalDate.now().minusYears(18).
                atStartOfDay(ZoneId.systemDefault()).toInstant());
        String sql = "SELECT mh FROM MembroHumano mh WHERE mh.dataDeNascimento < :dataminima AND mh.responsavel = false ORDER BY mh.nome";
        TypedQuery<MembroHumano> query = EM.createQuery(sql , MembroHumano.class);
        return query.setParameter("dataminima", dataMinima).getResultList();
    }
    
    public static List<MembroHumano> getPossiveisMembros() {
        String sql = "SELECT mh FROM MembroHumano mh WHERE mh.familia = null ORDER BY mh.nome";
        TypedQuery<MembroHumano> query = EM.createQuery(sql , MembroHumano.class);
        return query.getResultList();
    }
    
    public static boolean verificarResponsaveis() {
        Date dataMinima = Date.from(LocalDate.now().minusYears(18).
                atStartOfDay(ZoneId.systemDefault()).toInstant());
        String sql = "SELECT COUNT(*) FROM MembroHumano mh WHERE mh.dataDeNascimento < :dataminima AND mh.responsavel = false";
        TypedQuery<Long> query = EM.createQuery(sql , Long.class);
        return query.setParameter("dataminima", dataMinima).getSingleResult() > 0;
    }
    
    public static List<MembroHumano> getMembrosResponsaveis(Familia f) {
        String sql = "SELECT mh FROM MembroHumano mh INNER JOIN Membro m ON m.id = mh.id WHERE m.familia = :familia AND mh.responsavel = true";
        TypedQuery<MembroHumano> query = EM.createQuery(sql , MembroHumano.class);
        return query.setParameter("familia", f).getResultList();
    }
}
