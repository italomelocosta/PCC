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
@Table(name = "estoque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estoque.findItem", query = "SELECT e FROM Estoque e WHERE e.itemidItem = :itemidItem"),
    @NamedQuery(name = "Estoque.findAll", query = "SELECT e FROM Estoque e"),
    @NamedQuery(name = "Estoque.findByQuantidade", query = "SELECT e FROM Estoque e WHERE e.quantidade = :quantidade"),
    @NamedQuery(name = "Estoque.findByFornecedoridFornecedor", query = "SELECT e FROM Estoque e WHERE e.fornecedoridFornecedor = :fornecedoridFornecedor"),
    @NamedQuery(name = "Estoque.findByIdEstoque", query = "SELECT e FROM Estoque e WHERE e.idEstoque = :idEstoque")})
public class Estoque implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "quantidade")
    private float quantidade;
    @Basic(optional = false)
    @Column(name = "Fornecedor_idFornecedor")
    private int fornecedoridFornecedor;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstoque")
    private Integer idEstoque;
    @JoinColumn(name = "Item_idItem", referencedColumnName = "idItem")
    @ManyToOne(optional = false)
    private Item itemidItem;

    public Estoque() {
    }

    public Estoque(Integer idEstoque) {
        this.idEstoque = idEstoque;
    }

    public Estoque(Integer idEstoque, float quantidade, int fornecedoridFornecedor) {
        this.idEstoque = idEstoque;
        this.quantidade = quantidade;
        this.fornecedoridFornecedor = fornecedoridFornecedor;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public int getFornecedoridFornecedor() {
        return fornecedoridFornecedor;
    }

    public void setFornecedoridFornecedor(int fornecedoridFornecedor) {
        this.fornecedoridFornecedor = fornecedoridFornecedor;
    }

    public Integer getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Integer idEstoque) {
        this.idEstoque = idEstoque;
    }

    public Item getItemidItem() {
        return itemidItem;
    }

    public void setItemidItem(Item itemidItem) {
        this.itemidItem = itemidItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstoque != null ? idEstoque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estoque)) {
            return false;
        }
        Estoque other = (Estoque) object;
        if ((this.idEstoque == null && other.idEstoque != null) || (this.idEstoque != null && !this.idEstoque.equals(other.idEstoque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.Estoque[ idEstoque=" + idEstoque + " ]";
    }
    
}
