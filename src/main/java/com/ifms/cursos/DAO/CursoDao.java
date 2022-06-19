package com.ifms.cursos.DAO;
import com.ifms.cursos.Model.Curso;
import com.ifms.cursos.conexao.Conexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.HibernateException;


public class CursoDao implements ICursoDao {

    private static final String JPQL = "SELECT c FROM Curso c";

    private EntityManager getEntityManager() {
        return Conexao.createEntityManager();
    }

    @Override
    public List<Curso> buscarPorNome(String nome) {
        EntityManager em = getEntityManager();
        String condicao = "";
        List<Curso> Cursos = null;
        Boolean hasNome = nome != null && !nome.isBlank() && !nome.isEmpty();
        if (hasNome) {
            condicao = " WHERE c.nome LIKE ?1 ";
        }
        Query query = em.createQuery(JPQL + condicao);
        if (hasNome) {
            Cursos = query.setParameter(1, "%" + nome + "%")
                    .getResultList();
        } else {
            Cursos = query.getResultList();
        }
        em.close();
        return Cursos;
    }

    @Override
    public Curso inserir(Curso object) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (HibernateException ex) {
            em.getTransaction().rollback();
        }
        em.close();
        return object;
    }

    @Override
    public Curso alterar(Curso object) {
        EntityManager em = getEntityManager();
        em.detach(object);
        try {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
        } catch (HibernateException ex) {
            em.getTransaction().rollback();
        }
        em.close();
        return object;
    }

    @Override
    public void excluir(Object object) {
        Long id = (Long) object;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.getReference(Curso.class, id));
            em.getTransaction().commit();
        } catch (HibernateException ex) {
            em.getTransaction().rollback();
        }
        em.close();
    }

    @Override
    public List<Curso> listar() {
        return buscarPorNome(null);
    }

    @Override
    public Curso buscarPorId(Object object) {
        EntityManager em = getEntityManager();
        Long id = (Long) object;
        Curso obj = em.find(Curso.class, id);
        em.close();
        return obj;
    }

}
