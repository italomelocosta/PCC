/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import br.com.autooeste.Modelo.Estoque;
import br.com.autooeste.Modelo.Item;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.HibernateException;

/**
 *
 * @author Italo
 */
public class EstoqueDAO {

    private EntityManager entityManager;

    public EstoqueDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void salvar(Estoque estoque) {
        this.entityManager.persist(estoque);
    }

    public Estoque procurar(Item codigo) {
        Query q = entityManager.createNamedQuery("Estoque.findItem");
        q.setParameter("itemidItem", codigo);

        return (Estoque) q.getSingleResult();
    }

    public void alterar(Estoque estoque) {
        try {
            this.entityManager.merge(estoque);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }
}
