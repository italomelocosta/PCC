/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import br.com.autooeste.Modelo.Pedido;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Italo
 */

public class PedidoDAO {

    private EntityManager entityManager;
    
    public PedidoDAO(EntityManager em) {
        this.entityManager = em;
    }
    
    public void salvar(Pedido pedido){
        this.entityManager.persist(pedido);
    }
    public Pedido procurar(){
        Query q = entityManager.createNamedQuery("Pedido.lastReg");
      
        return (Pedido) q.getSingleResult();
    }
}
