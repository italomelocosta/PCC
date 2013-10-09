/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Controller;

import br.com.autooeste.DAO.NfentradaItemDAO;
import br.com.autooeste.Modelo.NfEntradaItem;
import br.com.autooeste.Util.Conexao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

/**
 *
 * @author Italo
 */
@ManagedBean
@RequestScoped
public class NfentradaItemController {
    private EntityManager em;
    private NfentradaItemDAO nfDAO;
    public NfentradaItemController() {
        em = Conexao.getEntityManager();
        em.getTransaction().begin();
        nfDAO = new NfentradaItemDAO(em);        
    }
    
    public void salvar(NfEntradaItem nfItem){
        try{
            nfDAO.salvar(nfItem);
            confirmarTransacao();
        }catch(Exception e){
            cancelarTransacao();
        }
    }
    
    private void confirmarTransacao() {
        em.getTransaction().commit();
        em.clear();
        em.getTransaction().begin();
    }

    private void cancelarTransacao() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        em.clear();
        em.getTransaction().begin();
    }
}
