/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Italo
 */
@Entity
@Table(name = "cotacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotacao.findAll", query = "SELECT c FROM Cotacao c"),
    @NamedQuery(name = "Cotacao.findByCotacaoId", query = "SELECT c FROM Cotacao c WHERE c.cotacaoId = :cotacaoId"),
    @NamedQuery(name = "Cotacao.findByValorItem", query = "SELECT c FROM Cotacao c WHERE c.valorItem = :valorItem"),
    @NamedQuery(name = "Cotacao.findByQuantidade", query = "SELECT c FROM Cotacao c WHERE c.quantidade = :quantidade")})
public class Cotacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cotacao_id")
    private Integer cotacaoId;
    @Basic(optional = false)
    @Column(name = "valor_item")
    private double valorItem;
    @Basic(optional = false)
    @Column(name = "quantidade")
    private double quantidade;
    @JoinColumn(name = "Fornecedor_idFornecedor", referencedColumnName = "idFornecedor")
    @ManyToOne(optional = false)
    private Fornecedor fornecedoridFornecedor;
    @JoinColumn(name = "Item_idItem", referencedColumnName = "idItem")
    @ManyToOne(optional = false)
    private Item itemidItem;
    @JoinColumn(name = "Pedido_idPedido", referencedColumnName = "idPedido")
    @ManyToOne(optional = false)
    private Pedido pedidoidPedido;

    public Cotacao() {
    }

    public Cotacao(Integer cotacaoId) {
        this.cotacaoId = cotacaoId;
    }

    public Cotacao(Integer cotacaoId, double valorItem, double quantidade) {
        this.cotacaoId = cotacaoId;
        this.valorItem = valorItem;
        this.quantidade = quantidade;
    }

    public Integer getCotacaoId() {
        return cotacaoId;
    }

    public void setCotacaoId(Integer cotacaoId) {
        this.cotacaoId = cotacaoId;
    }

    public double getValorItem() {
        return valorItem;
    }

    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Fornecedor getFornecedoridFornecedor() {
        return fornecedoridFornecedor;
    }

    public void setFornecedoridFornecedor(Fornecedor fornecedoridFornecedor) {
        this.fornecedoridFornecedor = fornecedoridFornecedor;
    }

    public Item getItemidItem() {
        return itemidItem;
    }

    public void setItemidItem(Item itemidItem) {
        this.itemidItem = itemidItem;
    }

    public Pedido getPedidoidPedido() {
        return pedidoidPedido;
    }

    public void setPedidoidPedido(Pedido pedidoidPedido) {
        this.pedidoidPedido = pedidoidPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cotacaoId != null ? cotacaoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotacao)) {
            return false;
        }
        Cotacao other = (Cotacao) object;
        if ((this.cotacaoId == null && other.cotacaoId != null) || (this.cotacaoId != null && !this.cotacaoId.equals(other.cotacaoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.Cotacao[ cotacaoId=" + cotacaoId + " ]";
    }
    
}
