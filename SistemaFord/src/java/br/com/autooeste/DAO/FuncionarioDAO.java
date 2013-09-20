/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import br.com.autooeste.Modelo.Funcionario;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Italo
 */
public class FuncionarioDAO {
    
    private EntityManager entityManager;
    
    public FuncionarioDAO(EntityManager em){
        this.entityManager = em;
    }
    
    public void salvar(Funcionario funcionario){
        this.entityManager.persist(funcionario);
    }
    
    public void remover(Funcionario funcionario){
        this.entityManager.persist(funcionario);
    }
    
    public Funcionario procurar(String login, String senha){
        //return this.entityManager.find(Funcionario.class, login);
        Query q = entityManager.createNamedQuery("Funcionario.findBySenha");
        q.setParameter("login", login);
        q.setParameter("senha", senha);
        return (Funcionario) q.getSingleResult();
    }
    
    public Funcionario procuraFunc(String login){
        Query q = entityManager.createNamedQuery("Funcionario.findByLogin");
        q.setParameter("login", login);
        return (Funcionario) q.getSingleResult();
    }
    
    public void atualizar(Funcionario funcionario){
        this.entityManager.merge(funcionario);
    }  
}
