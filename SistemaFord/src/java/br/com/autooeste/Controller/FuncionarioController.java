/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Controller;

import br.com.autooeste.DAO.FuncionarioDAO;
import br.com.autooeste.Modelo.Funcionario;
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
public class FuncionarioController {

    private FuncionarioDAO funcionarioDAO;
    private Funcionario func;
    private EntityManager em;

    public FuncionarioController() {
        em = Conexao.getEntityManager();
        funcionarioDAO = new FuncionarioDAO(em);
        em.getTransaction().begin();
        func = new Funcionario();
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }

    public String alterar(Funcionario funcionario) {
        String mensagem = validarFuncionario(funcionario);
        if (mensagem != null) {
            return mensagem;
        }
        try {
            funcionarioDAO.atualizar(funcionario);
            confirmarTransacao();
        } catch (Exception e) {
            cancelarTransacao();
            return e.getMessage();
        }
        return null;
    }

    public String salvar(Funcionario funcionario) {
        String mensagem = validarFuncionario(funcionario);
        if (mensagem != null) {
            return mensagem;
        }
        try {
            funcionarioDAO.salvar(funcionario);
            confirmarTransacao();
        } catch (Exception e) {
            cancelarTransacao();
            return e.getMessage();
        }
        return null;
    }

    public String remover(Funcionario funcionario) {
        try {
            funcionarioDAO.salvar(funcionario);
            confirmarTransacao();
        } catch (Exception e) {
            cancelarTransacao();
            return e.getMessage();
        }
        return null;
    }

    public List<Funcionario> buscarTodos() {
        Query query = em.createNamedQuery("Funcionario.findAll");
        return query.getResultList();
    }

    public String buscar() {
        Funcionario f = funcionarioDAO.procurar(func.getLogin(), func.getSenha());
        if (f != null) {
            return "index";
        }else
            return "login";
        
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

    private String validarFuncionario(Funcionario funcionario) {
        String mensagem = "";
        if (funcionario.getLogin().isEmpty()) {
            mensagem = "O Login não pode ser vazio.";
        }
        if (funcionario.getSenha().isEmpty()) {
            mensagem = "A senha não pode ser vazia.";
        }
        if (mensagem.isEmpty()) {
            return null;
        } else {
            return mensagem;
        }
    }
}
