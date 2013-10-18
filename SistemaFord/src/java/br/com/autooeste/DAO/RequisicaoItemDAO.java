/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import br.com.autooeste.Modelo.RequisicaoItem;
import javax.persistence.EntityManager;

/**
 *
 * @author Italo
 */
public class RequisicaoItemDAO {
    private EntityManager entityManager;
    
    public RequisicaoItemDAO(EntityManager em){
        this.entityManager = em;
    }
    
    public void salvar(RequisicaoItem rI){
        this.entityManager.persist(rI);
    }
    
}
