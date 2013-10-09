/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import br.com.autooeste.Modelo.NfEntrada;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Italo
 */
public class NfentradaDAO {
    private EntityManager entityManager;
    
    public NfentradaDAO(EntityManager em){
        this.entityManager = em;
    }
    
    public void salvar(NfEntrada nfentrada){
        this.entityManager.persist(nfentrada);
    }
    
    public NfEntrada procurar(){
        Query q = entityManager.createNamedQuery("NfEntrada.lastReg");
        try {  
        return (NfEntrada) q.getSingleResult();  
    } catch ( NoResultException nre ) {  
        return null;  
    }
        
    }
}
