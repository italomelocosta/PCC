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
        em.getTransaction().begin();
        pIDAO = new PedidoItemDAO(em);
    }
    
    public String salvar(PedidoItem pI) {
        /*String mensagem = validarFuncionario(funcionario);
        if (mensagem != null) {
            return mensagem;
        }*/
        try {
            pIDAO.salvar(pI);
            confirmarTransacao();
        } catch (Exception e) {
            cancelarTransacao();
            //return e.getMessage();
        }
        return "cadastro_Pedido";
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
