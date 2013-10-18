/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import br.com.autooeste.Modelo.Cotacao;
import javax.persistence.EntityManager;

/**
 *
 * @author Italo
 */
public class CotacaoDAO {
    private EntityManager entityManager;
    
    public CotacaoDAO(EntityManager em){
        this.entityManager = em;
    }
    
    public void salvar(Cotacao cotacao){
        this.entityManager.persist(cotacao);
    }
}
