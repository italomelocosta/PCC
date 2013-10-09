/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Controller;

import br.com.autooeste.DAO.FornecedorDAO;
import br.com.autooeste.Modelo.Fornecedor;
import br.com.autooeste.Util.Conexao;
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
public class FornecedorController {

    private FornecedorDAO fornDAO;
    private Fornecedor fornecedor;
    private EntityManager em;

    public FornecedorController() {
        em = Conexao.getEntityManager();
        em.getTransaction().begin();
        fornDAO = new FornecedorDAO(em);
        fornecedor = new Fornecedor();
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String salvar(Fornecedor forn) {
        /*String mensagem = validarFuncionario(funcionario);
         if (mensagem != null) {
         return mensagem;
         }*/
        try {
            fornDAO.salvar(forn);
            confirmarTransacao();
        } catch (Exception e) {
            cancelarTransacao();
            //return e.getMessage();
        }
        return "cadastro_Fornecedor";
    }

    public List<Fornecedor> buscarTodos() {
        Query query = em.createNamedQuery("Fornecedor.findAll");
        return query.getResultList();
    }

    public List<Fornecedor> getBuscar() {
        //System.out.println("\n\n\n\nBuscar");
        List<Fornecedor> lista = fornDAO.busca();
        return lista;
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
