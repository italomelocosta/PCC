/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Controller;

import br.com.autooeste.DAO.GrupoUserDAO;
import br.com.autooeste.Modelo.Grupouser;
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
public class GrupoUserController {

    private GrupoUserDAO cidadeDAO;
    private EntityManager em;

    public GrupoUserController() {
        em = Conexao.getEntityManager();
        cidadeDAO = new GrupoUserDAO(em);
        em.getTransaction().begin();
    }

    public String alterar(Grupouser gp) {
        String mensagem = validarCidade(gp);
        if (mensagem != null) {
            return mensagem;
        }
        try {
            cidadeDAO.atualizar(gp);
            confirmarTransacao();
        } catch (Exception e) {
            cancelarTransacao();
            return e.getMessage();
        }
        return null;
    }

    public String salvar(Grupouser gp) {
        String mensagem = validarCidade(gp);
        if (mensagem != null) {
            return mensagem;
        }
        try {
            cidadeDAO.salvar(gp);
            confirmarTransacao();
        } catch (Exception e) {
            cancelarTransacao();
            return e.getMessage();
        }
        return null;
    }

    public String remover(Grupouser gp) {
        try {
            cidadeDAO.remover(gp);
            confirmarTransacao();
        } catch (Exception e) {
            cancelarTransacao();
            return e.getMessage();
        }
        return null;
    }

    public List<Grupouser> buscarTodos() {
        Query query = em.createNamedQuery("Grupouser.findAll");
        return query.getResultList();
    }

    public List<Grupouser> buscar() {
        List<Grupouser> lista = cidadeDAO.procurar();
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

    private String validarCidade(Grupouser gp) {
        String mensagem = "";
        if (gp.getNomeGrupo().isEmpty()) {
            mensagem = "O nome não pode ser vazio.";
        }
        if (mensagem.isEmpty()) {
            return null;
        } else {
            return mensagem;
        }
    }
}
