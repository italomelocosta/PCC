/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Controller;

import br.com.autooeste.DAO.PedidoItemDAO;
import br.com.autooeste.Modelo.PedidoItem;
import br.com.autooeste.Util.Conexao;
import java.util.List;
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

    public List<PedidoItem> buscar(int codigo) {
        try {
            return pIDAO.buscar(codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

    public PedidoItem getpI() {
        return pI;
    }

    public void setpI(PedidoItem pI) {
        this.pI = pI;
    }
}
