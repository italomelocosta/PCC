/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Controller;

import br.com.autooeste.DAO.NfentradaDAO;
import br.com.autooeste.Modelo.Fornecedor;
import br.com.autooeste.Modelo.Item;
import br.com.autooeste.Modelo.NfEntrada;
import br.com.autooeste.Modelo.NfEntradaItem;
import br.com.autooeste.Modelo.Pedido;
import br.com.autooeste.Modelo.PedidoItem;
import br.com.autooeste.Util.Conexao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class NfentradaController implements Serializable {

    private NfentradaDAO nfDAO;
    private NfEntrada nf;
    private EntityManager em;
    private List<Item> lista;
    private Item item;
    private Fornecedor f;
    private Pedido p;
    private PedidoController pController;
    private NfEntradaItem nfItem;
    private NfentradaItemController nfItemController;
    private PedidoItemController pIController;

    public NfentradaController() {
        em = Conexao.getEntityManager();
        em.getTransaction().begin();
        nfDAO = new NfentradaDAO(em);
        nf = new NfEntrada();
        f = new Fornecedor();
        p = new Pedido();
        pController = new PedidoController();
        //nfItem = new NfEntradaItem();
        pIController = new PedidoItemController();
        nfItemController = new NfentradaItemController();
        lista = new ArrayList<Item>();
        item = new Item();
    }

    public void novoItem(Item itens) {
        if (lista == null) {
            lista = new ArrayList<Item>();
        }
        itens.setQuantida(item.getQuantida());
        itens.setValor(item.getValor());
        lista.add(itens);
    }

    public String salvar() {
        nf.setFornecedoridFornecedor(f);
        try {
            nfDAO.salvar(nf);
            confirmarTransacao();
        } catch (Exception e) {
            cancelarTransacao();
        }
        int codigo = p.getIdPedido();
        NfEntrada buscaNota = nfDAO.procurar();
        Pedido retorno = pController.buscar(codigo);
        
        retorno.setNfEntradaId(buscaNota);
        retorno.setValor(nf.getValorTotal());
        retorno.setFornecedoridFornecedor(f);
        retorno.setStatus(2);
        try {
            pController.atualizar(retorno);
            confirmarTransacao();
        } catch (Exception e) {
            e.printStackTrace();
            cancelarTransacao();
        }
        int tamanho = lista.size() - 1;
        //System.out.println("\n\n\n\n" + tamanho);
        for (int j = 0; j <= tamanho; j++) {
            nfItem = new NfEntradaItem();
            nfItem.setNfEntradaIdnfEntrada(buscaNota);
            //System.out.println("\n\n\n\n" + lista.get(j).getDescricao());
            nfItem.setItemidItem(lista.get(j));
            nfItem.setQuantidadeComprada(lista.get(j).getQuantida());
            nfItem.setValor(lista.get(j).getValor());
            try {
                nfItemController.salvar(nfItem);
                confirmarTransacao();
            } catch (Exception e) {
                cancelarTransacao();
            }
        }
        return null;
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

    public NfEntrada getNf() {
        return nf;
    }

    public void setNf(NfEntrada nf) {
        this.nf = nf;
    }

    public List<Item> getLista() {
        return lista;
    }

    public void setLista(List<Item> lista) {
        this.lista = lista;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Fornecedor getF() {
        return f;
    }

    public void setF(Fornecedor f) {
        this.f = f;
    }

    public Pedido getP() {
        return p;
    }

    public void setP(Pedido p) {
        this.p = p;
    }
}
