/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Controller;

import br.com.autooeste.DAO.PedidoItemDAO;
import br.com.autooeste.Modelo.PedidoItem;
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
public class PedidoItemController {

    private EntityManager em;
    private PedidoItem pI;
    private PedidoItemDAO pIDAO;

    public PedidoItemController() {
        this.em = Conexao.getEntityManager();
        pIDAO = new PedidoItemDAO(em);
        if (em.isOpen()) {
            System.out.println("Aberto");
        }
        em.getTransaction().begin();

    }

    public void salvar(PedidoItem pI) {
        //System.out.println("\n\n\n\n\nPedidoItemController");
        try {
            pIDAO.salvar(pI);
            confirmarTransacao();
        } catch (Exception e) {
            cancelarTransacao();
            //return e.getMessage();
        }
        //return "cadastro_Pedido";
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

    public PedidoItem getpI() {
        return pI;
    }

    public void setpI(PedidoItem pI) {
        this.pI = pI;
    }
}
