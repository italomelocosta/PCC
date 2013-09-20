/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import br.com.autooeste.Modelo.Grupouser;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Italo
 */
public class GrupoUserDAO {
    private EntityManager entityManager;
    
    public GrupoUserDAO(EntityManager em){
        this.entityManager = em;
    }
    
    public void salvar(Grupouser gp){
        this.entityManager.persist(gp);
    }
    
    public void remover(Grupouser gp){
        this.entityManager.persist(gp);
    }
    
    public List procurar(){
        Query q = entityManager.createNamedQuery("Grupouser.findAll");
        return q.getResultList();
        //return this.entityManager.find(Grupouser.class, id);
    }
    
    public void atualizar(Grupouser gp){
        this.entityManager.merge(gp);
    }

}
