/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import javax.persistence.EntityManager;

/**
 *
 * @author Italo
 */
public class ItemDAO {
    private EntityManager entityManager;
    
    public ItemDAO(EntityManager em){
        this.entityManager = em;
    }
    
}
