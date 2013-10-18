/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import br.com.autooeste.Modelo.PedidoItem;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Italo
 */
public class PedidoItemDAO {

    private EntityManager entityManager;

    public PedidoItemDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void salvar(PedidoItem pI) {
        //System.out.println("\n\n\n\n\nDAO");
        this.entityManager.persist(pI);
    }

    public List<PedidoItem> buscar(int codigo) {
        Query q = entityManager.createNamedQuery("PedidoItem.findByPedidoItem");
        q.setParameter("pedidoidPedido", codigo);
        try {
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
