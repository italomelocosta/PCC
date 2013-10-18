/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.DAO;

import br.com.autooeste.Modelo.RequisicaoEstoque;
import br.com.autooeste.Util.Conexao1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Italo
 */
public class RequisicaoEstoqueDAO {

    private EntityManager entityManager;

    public RequisicaoEstoqueDAO(EntityManager em) {
        this.entityManager = em;
    }
    
    public void salvar(RequisicaoEstoque rq){
        this.entityManager.persist(rq);
    }
    
    public RequisicaoEstoque procurar(){
        Query q = entityManager.createNamedQuery("RequisicaoEstoque.lastReg");     
        return (RequisicaoEstoque) q.getSingleResult();
    }
    
    public ResultSet procurar2(int codigo){
        ResultSet rs = null;
        String sql = "SELECT re.idRequisicao_estoque, re.dt_requisicao, re.mot_solicitacao, f.nome, ri.Item_idItem, i.descricao, ri.quantidade_requerida, i.medida FROM Requisicao_Estoque re INNER JOIN Funcionario f ON re.Funcionario_idFuncionario = f.idFuncionario INNER JOIN Requisicao_item ri ON re.idRequisicao_estoque = ri.Requisicao_estoque_idRequisicao_estoque INNER JOIN Item i ON ri.Item_idItem = i.idItem where re.idRequisicao_estoque = ?";
        try {
            Connection conexao = Conexao1.getConnection();
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, codigo);            
            
            rs = pst.executeQuery(sql);
            
            
        }catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
        return rs;
    }
}
