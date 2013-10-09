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
    private List<Item> lista2;
    private Item item;
    private int cod;
    private ItemDAO iDAO;
    
    public ItemController() {
        em = Conexao.getEntityManager();
        em.getTransaction().begin();
        this.item = new Item();
        lista = buscarTodos();
        lista2 = new ArrayList<Item>();
        iDAO = new ItemDAO(em);
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
    
    public String busca(){
        System.out.println("\n\n\n\n\n\n\n"+item.getIdItem()+"\n\n\n\n\n\n");
        //lista2.add(lista.get(cod));
        //Item i = new Item();
        //int coco = item.getIdItem();
        //i = iDAO.procurar(coco);
        //lista2.add(i);
        return null;
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
    
    public List<Item> getLista2() {
        return lista2;
    }

    public void setLista2(List<Item> lista2) {
        this.lista2 = lista2;
    }
}
