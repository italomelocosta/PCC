/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import br.com.autooeste.Modelo.Endereco;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.Query;

/**
 *
 * @author Italo
 */
public class EnderecoDAO {
    
    private EntityManager entityManager;
    
    public EnderecoDAO(EntityManager em){
        this.entityManager = em;
    }
    
    public void salvar(Endereco endereco){
        this.entityManager.persist(endereco);
    }
    
    public void remover(Endereco endereco){
        this.entityManager.persist(endereco);
    }
    
    public Endereco procurar(){
        Query q = entityManager.createNamedQuery("Endereco.lastReg");
      
        return (Endereco) q.getSingleResult();
    }
    
    public void atualizar(Endereco endereco){
        this.entityManager.merge(endereco);
    }
}
