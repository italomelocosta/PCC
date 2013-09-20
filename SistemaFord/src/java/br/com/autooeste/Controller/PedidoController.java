/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Controller;

import br.com.autooeste.DAO.PedidoDAO;
import br.com.autooeste.Modelo.Funcionario;
import br.com.autooeste.Modelo.Item;
import br.com.autooeste.Modelo.Pedido;
import br.com.autooeste.Modelo.PedidoItem;
import br.com.autooeste.Util.Conexao;
import java.util.ArrayList;
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
public class PedidoController {

    private EntityManager em;
    private Pedido ped;
    private PedidoDAO pDAO;
    private List<Item> i;
    private Item item;
    private PedidoItem pI;
    private PedidoItemController pIController;
    private Funcionario func;
    private FuncionarioController fController;

    public PedidoController() {
        em = Conexao.getEntityManager();
        em.getTransaction().begin();
        ped = new Pedido();
        pDAO = new PedidoDAO(em);
        i = new ArrayList<Item>();
        pIController = new PedidoItemController();
        func = new Funcionario();
        fController = new FuncionarioController();
        pI = new PedidoItem();
    }
    
    public void novoItem() {
        this.item = new Item();
    }

    public String salvar() {
        Funcionario buscaFunc = fController.buscaFunc(func);
        ped.setAprovado(0);
        ped.setFuncionarioidFuncionario(buscaFunc);
        try {
            pDAO.salvar(ped);
            confirmarTransacao();
        } catch (Exception e) {
            cancelarTransacao();
            //return e.getMessage();
        }
        Pedido retorno = pDAO.procurar();
        pI.setPedido(retorno);
        
        int j = 0;
        while (!i.isEmpty()) {
            pI.setItem(i.get(j));
            pIController.salvar(pI);
            i.remove(j);
            j++;
        }
        return "cadastro_Fornecedor";
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

    public Pedido getPed() {
        return ped;
    }

    public void setPed(Pedido ped) {
        this.ped = ped;
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }

    public List<Item> getI() {
        return i;
    }

    public void setI(List<Item> i) {
        this.i = i;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public PedidoItem getpI() {
        return pI;
    }

    public void setpI(PedidoItem pI) {
        this.pI = pI;
    }
    
    
}
