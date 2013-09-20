/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Controller;

import br.com.autooeste.Modelo.Item;
import br.com.autooeste.Util.Conexao;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Italo
 */
@ManagedBean
@RequestScoped
public class ItemController {

    private EntityManager em;
    private List<Item> lista = new ArrayList<Item>();
    
    public ItemController() {
        em = Conexao.getEntityManager();
        em.getTransaction().begin();
        lista = buscarTodos();
    }

    public List<Item> getLista() {
        return lista;
    }

    public void setLista(List<Item> lista) {
        this.lista = lista;
    }

    private List<Item> buscarTodos() {
        Query query = em.createNamedQuery("Item.findAll");
        return query.getResultList();
    }

}
