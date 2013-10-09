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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Italo
 */
@ManagedBean
@SessionScoped
public class PedidoController implements Serializable {

    private EntityManager em;
    private Pedido ped;
    private PedidoDAO pDAO;
    private List<Item> i = null;
    private PedidoItem pI;
    private PedidoItemController pIController;
    private Funcionario func;
    private FuncionarioController fController;
    private int coco = 0;
    private Item item;

    public PedidoController() {
        em = Conexao.getEntityManager();
        em.getTransaction().begin();
        ped = new Pedido();
        pDAO = new PedidoDAO(em);
        pIController = new PedidoItemController();
        func = new Funcionario();
        fController = new FuncionarioController();
        pI = new PedidoItem();
        item = new Item();
    }

    public void novoItem(Item itens) {
        if (i == null) {
            i = new ArrayList<Item>();
        }
        //System.out.println("\n\n\n\n\n" + item.getQuantida());
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest rq = (HttpServletRequest) context.getRequest();
        itens.setQuantida(item.getQuantida());
        i.add(itens);
    }

    public String salvar() {
        //ItemController ic = new ItemController();
        //i = (ArrayList<Item>) ic.getLista();
        //System.out.println("\n\n\n\n" + func.getLogin());
        Funcionario buscaFunc = fController.buscaFunc(func);
        ped.setAprovado(0);
        ped.setStatus(0);
        ped.setFuncionarioidFuncionario(buscaFunc);
        try {
            pDAO.salvar(ped);
            confirmarTransacao();
        } catch (Exception e) {
            cancelarTransacao();
            //return e.getMessage();
        }
        Pedido retorno = pDAO.procurar();
        pI.setPedidoidPedido(retorno);

        int tamanho = i.size() - 1;
        for (int j = 0; j <= tamanho; j++) {
            pI.setQuantidadePedido(i.get(j).getQuantida());
            pI.setItemidItem(i.get(j));
            try {
                pIController.salvar(pI);
            } catch (Exception e) {
                cancelarTransacao();
            }
            //i.remove(j);
            //j++;
        }
        return "cadastro_pedido";
    }
    
    public void atualizar(Pedido pedido){
        try{
            pDAO.atualizar(pedido);
            confirmarTransacao();
        }catch(Exception e){
            cancelarTransacao();
        }
    }
    
    public Pedido buscar(int codigo){
        return pDAO.buscarPedido(codigo);
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

    public void setI(ArrayList<Item> i) {
        this.i = i;
    }

    public PedidoItem getpI() {
        return pI;
    }

    public void setpI(PedidoItem pI) {
        this.pI = pI;
    }

    public int getCoco() {
        return coco;
    }

    public void setCoco(int coco) {
        this.coco = coco;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
