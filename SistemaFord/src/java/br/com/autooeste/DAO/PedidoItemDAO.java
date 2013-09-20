/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import br.com.autooeste.Modelo.PedidoItem;
import javax.persistence.EntityManager;

/**
 *
 * @author Italo
 */
public class PedidoItemDAO {
    
    private EntityManager entityManager;
    
    public PedidoItemDAO(EntityManager em){
        this.entityManager = em;
    }
    
    public void salvar(PedidoItem pI){
        this.entityManager.persist(pI);
    }
    
}
