/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import br.com.autooeste.Modelo.Pedido;
import java.util.List;
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
    
    public Pedido buscarPedido(int codigo){
        Query q = entityManager.createNamedQuery("Pedido.findByIdPedido");
        q.setParameter("idPedido", codigo);
        
        return (Pedido) q.getSingleResult();
    }
    
    public void atualizar(Pedido pedido){
        this.entityManager.merge(pedido);
    }
    
    public List<Pedido> buscaAprovacao(){
        Query q = entityManager.createNamedQuery("Pedido.findDesaprovado");
        return q.getResultList();
    }
    
    public List<Pedido> buscaAprovados(){
        Query q = entityManager.createNamedQuery("Pedido.findAprovado");
        return q.getResultList();
    }
}
