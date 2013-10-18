/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Controller;

import br.com.autooeste.DAO.CotacaoDAO;
import br.com.autooeste.Modelo.Cotacao;
import br.com.autooeste.Modelo.Fornecedor;
import br.com.autooeste.Modelo.Item;
import br.com.autooeste.Modelo.Pedido;
import br.com.autooeste.Util.Conexao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

/**
 *
 * @author Italo
 */
@ManagedBean
@SessionScoped
public class CotacaoController {

    private EntityManager em;
    private Cotacao c;
    private CotacaoDAO cDAO;
    private Fornecedor f;
    private FornecedorController fController;
    private Pedido p;
    private PedidoController pController;
    private List<Item> lista;
    private Item item;

    public CotacaoController() {
        em = Conexao.getEntityManager();
        em.getTransaction().begin();
        c = new Cotacao();
        cDAO = new CotacaoDAO(em);
        f = new Fornecedor();
        fController = new FornecedorController();
        p = new Pedido();
        pController = new PedidoController();
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

    public void salvar() {
        int tamanho = lista.size() - 1;
        for (int j = 0; j <= tamanho; j++) {
            //rI = new RequisicaoItem();
            c = new Cotacao();
            c.setPedidoidPedido(p);
            c.setFornecedoridFornecedor(f);
            c.setValorItem(lista.get(j).getValor());
            c.setQuantidade(lista.get(j).getQuantida());
            c.setItemidItem(lista.get(j));
            try {
                cDAO.salvar(c);
                confirmarTransacao();
            } catch (Exception e) {
                e.printStackTrace();
                cancelarTransacao();
            }
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

    public Cotacao getC() {
        return c;
    }

    public void setC(Cotacao c) {
        this.c = c;
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getLista() {
        return lista;
    }

    public void setLista(List<Item> lista) {
        this.lista = lista;
    }
}
