/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
    @NamedQuery(name = "Estoque.findAll", query = "SELECT e FROM Estoque e"),
    @NamedQuery(name = "Estoque.findByItemidItem", query = "SELECT e FROM Estoque e WHERE e.itemidItem = :itemidItem"),
    @NamedQuery(name = "Estoque.findByQuantidade", query = "SELECT e FROM Estoque e WHERE e.quantidade = :quantidade")})
public class Estoque implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Item_idItem")
    private Integer itemidItem;
    @Basic(optional = false)
    @Column(name = "quantidade")
    private int quantidade;
    @JoinColumn(name = "Fornecedor_idFornecedor", referencedColumnName = "idFornecedor")
    @ManyToOne(optional = false)
    private Fornecedor fornecedoridFornecedor;
    @JoinColumn(name = "Item_idItem", referencedColumnName = "idItem", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Item item;

    public Estoque() {
    }

    public Estoque(Integer itemidItem) {
        this.itemidItem = itemidItem;
    }

    public Estoque(Integer itemidItem, int quantidade) {
        this.itemidItem = itemidItem;
        this.quantidade = quantidade;
    }

    public Integer getItemidItem() {
        return itemidItem;
    }

    public void setItemidItem(Integer itemidItem) {
        this.itemidItem = itemidItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Fornecedor getFornecedoridFornecedor() {
        return fornecedoridFornecedor;
    }

    public void setFornecedoridFornecedor(Fornecedor fornecedoridFornecedor) {
        this.fornecedoridFornecedor = fornecedoridFornecedor;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemidItem != null ? itemidItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estoque)) {
            return false;
        }
        Estoque other = (Estoque) object;
        if ((this.itemidItem == null && other.itemidItem != null) || (this.itemidItem != null && !this.itemidItem.equals(other.itemidItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.Estoque[ itemidItem=" + itemidItem + " ]";
    }
    
}
