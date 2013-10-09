/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import br.com.autooeste.Modelo.NfEntradaItem;
import javax.persistence.EntityManager;

/**
 *
 * @author Italo
 */
public class NfentradaItemDAO {
    private EntityManager entityManager;
    
    public NfentradaItemDAO(EntityManager em){
        this.entityManager = em;
    }
    
    public void salvar(NfEntradaItem nfeI){
        this.entityManager.persist(nfeI);
    }
}
