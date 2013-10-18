/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Controller;

import br.com.autooeste.DAO.EstoqueDAO;
import br.com.autooeste.Modelo.Estoque;
import br.com.autooeste.Modelo.Fornecedor;
import br.com.autooeste.Modelo.Item;
import br.com.autooeste.Util.Conexao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

/**
 *
 * @author Italo
 */
@ManagedBean
@SessionScoped
public class EstoqueController {

    private Estoque e;
    private EstoqueDAO eDAO;
    private Item i;
    private Fornecedor f;
    private EntityManager em;
    private List<Item> lista = null;

    public EstoqueController() {
        em = Conexao.getEntityManager();
        em.getTransaction().begin();
        e = new Estoque();
        eDAO = new EstoqueDAO(em);
        i = new Item();
        f = new Fornecedor();
    }

    public void novoItem(Item itens) {
        if (lista == null) {
            lista = new ArrayList<Item>();
        }
        itens.setQuantida(i.getQuantida());
        lista.add(itens);
    }

    public void salvar() {
        int tamanho = lista.size() - 1;
        for (int j = 0; j <= tamanho; j++) {
            e = new Estoque();
            e.setItemidItem(lista.get(j));
            e.setFornecedoridFornecedor(f.getIdFornecedor());
            e.setQuantidade(lista.get(j).getQuantida());
            try {
                eDAO.salvar(e);
                confirmarTransacao();
            } catch (Exception e) {
                e.printStackTrace();
                cancelarTransacao();
            }
        }
        lista.removeAll(lista);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("EstoqueController", new EstoqueController());
    }

    public Estoque buscar(Item i) {
        try {
            return eDAO.procurar(i);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void alterar(Estoque estoque) {
        try {
            eDAO.alterar(estoque);
        } catch (Exception ex) {
            ex.printStackTrace();
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

    public Fornecedor getF() {
        return f;
    }

    public void setF(Fornecedor f) {
        this.f = f;
    }

    public List<Item> getLista() {
        return lista;
    }

    public void setLista(List<Item> lista) {
        this.lista = lista;
    }

    public Item getI() {
        return i;
    }

    public void setI(Item i) {
        this.i = i;
    }
}
