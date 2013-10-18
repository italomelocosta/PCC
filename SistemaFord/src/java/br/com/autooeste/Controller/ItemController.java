/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Controller;

import br.com.autooeste.DAO.ItemDAO;
import br.com.autooeste.Modelo.Item;
import br.com.autooeste.Util.Conexao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Italo
 */
@ManagedBean
@RequestScoped
public class ItemController implements Serializable {

    private EntityManager em;
    private List<Item> lista = new ArrayList<Item>();
    private Item item;
    private int cod;
    private ItemDAO iDAO;

    public ItemController() {
        em = Conexao.getEntityManager();
        em.getTransaction().begin();
        this.item = new Item();
        lista = buscarTodos();
        iDAO = new ItemDAO(em);
    }

    public void salvar() {
        try {
            iDAO.salvar(item);
            confirmarTransacao();
        } catch (Exception e) {
            cancelarTransacao();
        }
    }

    public List<Item> getLista() {
        return lista;
    }

    public void setLista(List<Item> lista) {
        this.lista = lista;
    }

    public List<Item> buscarTodos() {
        //System.out.println("\n\n\n\n\n\n\nteste testes\n\n\n\n\n\n");
        Query query = em.createNamedQuery("Item.findAll");
        return query.getResultList();
        //return iDAO.buscaTodos();
        //return null;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
