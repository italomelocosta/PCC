/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import br.com.autooeste.Modelo.Fornecedor;
import javax.persistence.EntityManager;

/**
 *
 * @author Italo
 */
public class FornecedorDAO {
     private EntityManager entityManager;
    
    public FornecedorDAO(EntityManager em){
        this.entityManager = em;
    }
    
    public void salvar(Fornecedor fornecedor){
        this.entityManager.persist(fornecedor);
    }
}
