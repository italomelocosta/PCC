/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Controller;

import br.com.autooeste.DAO.RequisicaoItemDAO;
import br.com.autooeste.Modelo.RequisicaoItem;
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
public class RequisicaoItemController {

    private EntityManager em;
    private RequisicaoItemDAO riDAO;
    
    public RequisicaoItemController() {
        this.em = Conexao.getEntityManager();
        this.em.getTransaction().begin();
        riDAO = new RequisicaoItemDAO(em);
    }
    
    public void salvar(RequisicaoItem rI) {
        try {
            riDAO.salvar(rI);
            confirmarTransacao();
        } catch (Exception e) {
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
