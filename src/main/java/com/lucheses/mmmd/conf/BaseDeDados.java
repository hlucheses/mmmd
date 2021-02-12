package com.lucheses.mmmd.conf;

import com.lucheses.mmmd.entidades.Credito;
import com.lucheses.mmmd.entidades.Familia;
import com.lucheses.mmmd.entidades.Gasto;
import com.lucheses.mmmd.entidades.MembroHumano;
import com.lucheses.mmmd.entidades.PrevisaoMensal;
import com.lucheses.mmmd.entidades.Rendimento;
import com.lucheses.mmmd.entidades.Utilizador;
import java.time.Instant;
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

    public static EntityManager EM;
    public static EntityManagerFactory EMF;

    static {
        comecar();
    }

    public static void comecar() {
        EMF = Persistence.createEntityManagerFactory("MyMoneyMyDecisionPU");
        EM = EMF.createEntityManager();
    }
    
    public static void reiniciar() {
        EM.close();
        comecar();
    }
    
    public static void comitar() {
        EM.getTransaction().begin();
        EM.getTransaction().commit();
    }

    public static void remover(Object o) {
        EM.getTransaction().begin();
        EM.remove(o);
        EM.getTransaction().commit();
    }

    public static void persistir(Object o) {
        EM.getTransaction().begin();
        EM.persist(o);
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
        return query.setParameter("familia", f).setMaxResults(1).getSingleResult();
    }

    public static PrevisaoMensal getPrevisaoMensalById(int id) {
        String sql = "SELECT pm FROM PrevisaoMensal pm WHERE pm.id = :id";
        TypedQuery<PrevisaoMensal> query = EM.createQuery(sql, PrevisaoMensal.class);
        return query.setParameter("id", id).setMaxResults(1).getSingleResult();
    }

    public static List<PrevisaoMensal> buscarPrevisoesFamilia(Familia f) {
        String sql = "SELECT pm FROM PrevisaoMensal pm WHERE pm.familia = :familia ORDER BY pm.dataPrevisao DESC";
        TypedQuery<PrevisaoMensal> query = EM.createQuery(sql, PrevisaoMensal.class);
        return query.setParameter("familia", f).getResultList();
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
        PrevisaoMensal pm = query.setParameter("familia", f).setMaxResults(1).getSingleResult();
        LocalDate dataNovaPrevisao = Instant.ofEpochMilli(pm.getDataPrevisao()
                .getTime()).atZone(ZoneId.systemDefault()).toLocalDate().plusMonths(1);
        return java.sql.Date.valueOf(dataNovaPrevisao);
    }

    public static boolean todosOsCreditosPagos(Familia familia) {
        String sql = "SELECT COUNT(*) FROM Credito c WHERE c.autorGasto.familia = :familia AND c.valorTotal > c.valorPago";
        TypedQuery<Long> query = EM.createQuery(sql, Long.class);
        return query.setParameter("familia", familia).getSingleResult() <= 0;
    }

    public static Credito getCredito(Familia familia) {
        String sql = "SELECT c FROM Credito c WHERE c.autorGasto.familia = :familia AND c.valorTotal > c.valorPago";
        TypedQuery<Credito> query = EM.createQuery(sql, Credito.class);
        return query.setParameter("familia", familia).getSingleResult();
    }

    private BaseDeDados() {
    }

    public static Query novoQuery(String sql) {
        return EM.createQuery(sql);
    }

    public static Utilizador getUtilizadorByUsername(String username) {
        String sql = "SELECT u FROM Utilizador u WHERE u.username = :username";
        TypedQuery<Utilizador> query = EM.createQuery(sql, Utilizador.class);
        return query.setParameter("username", username).getSingleResult();
    }

    public static MembroHumano getMembroHumanoByUsername(String username) {
        String sql = "SELECT mh FROM MembroHumano mh WHERE mh.utilizador.username = :username";
        TypedQuery<MembroHumano> query = EM.createQuery(sql, MembroHumano.class);
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
        TypedQuery<Familia> query = EM.createQuery(sql, Familia.class);
        return query.getResultList();
    }

    public static List<MembroHumano> getMembrosFamilia(Familia f) {
        String sql = "SELECT mh FROM MembroHumano mh WHERE mh.familia = :familia ORDER BY mh.nome";
        TypedQuery<MembroHumano> query = EM.createQuery(sql, MembroHumano.class);
        return query.setParameter("familia", f).getResultList();
    }

    public static List<MembroHumano> getPossiveisResponsaveis() {
        Date dataMinima = Date.from(LocalDate.now().minusYears(18).
                atStartOfDay(ZoneId.systemDefault()).toInstant());
        String sql = "SELECT mh FROM MembroHumano mh WHERE mh.dataDeNascimento < :dataminima AND mh.familia = null AND mh.utilizador != null ORDER BY mh.nome";
        TypedQuery<MembroHumano> query = EM.createQuery(sql, MembroHumano.class);
        return query.setParameter("dataminima", dataMinima).getResultList();
    }

    public static List<MembroHumano> getPossiveisMembros() {
        String sql = "SELECT mh FROM MembroHumano mh WHERE mh.familia = null ORDER BY mh.nome";
        TypedQuery<MembroHumano> query = EM.createQuery(sql, MembroHumano.class);
        return query.getResultList();
    }

    public static boolean verificarResponsaveis() {
        Date dataMinima = Date.from(LocalDate.now().minusYears(18).
                atStartOfDay(ZoneId.systemDefault()).toInstant());
        String sql = "SELECT COUNT(*) FROM MembroHumano mh WHERE mh.dataDeNascimento < :dataminima AND mh.familia = null";
        TypedQuery<Long> query = EM.createQuery(sql, Long.class);
        return query.setParameter("dataminima", dataMinima).getSingleResult() > 0;
    }

    public static List<Gasto> getGastosFamilia(Familia f) {
        String sql = "SELECT g FROM Gasto g WHERE g.autorGasto.familia = :familia";
        TypedQuery<Gasto> query = EM.createQuery(sql, Gasto.class);
        return query.setParameter("familia", f).getResultList();
    }

    public static List<Rendimento> getRendimentosFamilia(Familia f) {
        String sql = "SELECT r FROM Rendimento r WHERE r.beneficiarioRendimento.familia = :familia";
        TypedQuery<Rendimento> query = EM.createQuery(sql, Rendimento.class);
        return query.setParameter("familia", f).getResultList();
    }

    public static List<MembroHumano> getMembrosResponsaveis(Familia f) {
        String sql = "SELECT mh FROM MembroHumano mh INNER JOIN Membro m ON m.id = mh.id WHERE m.familia = :familia AND mh.responsavel = true";
        TypedQuery<MembroHumano> query = EM.createQuery(sql, MembroHumano.class);
        return query.setParameter("familia", f).getResultList();
    }
}
