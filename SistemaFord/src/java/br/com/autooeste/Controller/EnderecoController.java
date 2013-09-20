/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Controller;

import br.com.autooeste.DAO.EnderecoDAO;
import br.com.autooeste.DAO.FuncionarioDAO;
import br.com.autooeste.Modelo.Endereco;
import br.com.autooeste.Modelo.Fornecedor;
import br.com.autooeste.Modelo.Funcionario;
import br.com.autooeste.Modelo.Grupouser;
import br.com.autooeste.Util.Conexao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import org.hibernate.HibernateException;

/**
 *
 * @author Italo
 */
@ManagedBean
@RequestScoped
public class EnderecoController {

    /**
     * Creates a new instance of EnderecoController
     */
    private EnderecoDAO enderecoDAO;
    private Endereco end;
    private EntityManager em;
    private FuncionarioController func;
    private Funcionario funcionario;
    private Grupouser gp;
    private Fornecedor forn;
    private FornecedorController fornecedor;

    public EnderecoController() {
        em = Conexao.getEntityManager();
        enderecoDAO = new EnderecoDAO(em);
        em.getTransaction().begin();
        end = new Endereco();
        func = new FuncionarioController();
        funcionario = new Funcionario();
        gp = new Grupouser();
        forn = new Fornecedor();
        fornecedor = new FornecedorController();
    }

    public Fornecedor getForn() {
        return forn;
    }

    public void setForn(Fornecedor forn) {
        this.forn = forn;
    }
    
    public Grupouser getGp() {
        return gp;
    }

    public void setGp(Grupouser gp) {
        this.gp = gp;
    }

    public Endereco getEnd() {
        return end;
    }

    public void setEnd(Endereco end) {
        this.end = end;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    } 

    public String salvar() {
        try {
            enderecoDAO.salvar(end);
            confirmarTransacao();
        } catch (HibernateException e) {
            cancelarTransacao();
        }
        Endereco e = new Endereco();
        e = buscar();
        funcionario.setEnderecoidEndereco(e);
        //System.out.println(e.getIdEndereco());
        funcionario.setGrupoUseridGrupoUser(gp);
        //System.out.println(gp.getIdGrupoUser());
        String retorno = func.salvar(funcionario);
        return "cadastro_Func";
    }
    
    public String salvarForn() {
        try {
            enderecoDAO.salvar(end);
            confirmarTransacao();
        } catch (HibernateException e) {
            cancelarTransacao();
        }
        Endereco e = new Endereco();
        e = buscar();
        forn.setEnderecoidEndereco(e);
        //System.out.println(e.getIdEndereco());
        //funcionario.setGrupoUseridGrupoUser(gp);
        //System.out.println(gp.getIdGrupoUser());
        String retorno = fornecedor.salvar(forn);
        return "cadastro_Func";
    }

    public Endereco buscar() {

        return enderecoDAO.procurar();
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
