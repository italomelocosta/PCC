/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import br.com.autooeste.Modelo.Fornecedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Italo
 */
public class FornecedorDAO {

    private EntityManager entityManager;

    public FornecedorDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void salvar(Fornecedor fornecedor) {
        this.entityManager.persist(fornecedor);
    }

    public List busca() {
        Query q = entityManager.createNamedQuery("Fornecedor.findAll");
        return q.getResultList();
    }
}
