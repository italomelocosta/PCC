/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import br.com.autooeste.Modelo.Item;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Italo
 */
public class ItemDAO {
    private EntityManager entityManager;
    
    public ItemDAO(EntityManager em){
        this.entityManager = em;
    }
    
    public Item procurar(int cod){
        System.out.println("\n\n\n\n\n\n\n"+cod+"\n\n\n\n\n\n");
        return this.entityManager.find(Item.class, cod);
    }
    
    public List<Item> buscaTodos(){
        System.out.println("\n\n\n\n\n\n\nDAO\n\n\n\n");
        Query query = entityManager.createNamedQuery("Item.findAll");
        return query.getResultList();
    }
}
