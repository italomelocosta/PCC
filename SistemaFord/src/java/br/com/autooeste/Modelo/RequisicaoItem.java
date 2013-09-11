/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "requisicao_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RequisicaoItem.findAll", query = "SELECT r FROM RequisicaoItem r"),
    @NamedQuery(name = "RequisicaoItem.findByItemidItem", query = "SELECT r FROM RequisicaoItem r WHERE r.requisicaoItemPK.itemidItem = :itemidItem"),
    @NamedQuery(name = "RequisicaoItem.findByRequisicaoestoqueidRequisicaoestoque", query = "SELECT r FROM RequisicaoItem r WHERE r.requisicaoItemPK.requisicaoestoqueidRequisicaoestoque = :requisicaoestoqueidRequisicaoestoque"),
    @NamedQuery(name = "RequisicaoItem.findByQuantidadeRequerida", query = "SELECT r FROM RequisicaoItem r WHERE r.quantidadeRequerida = :quantidadeRequerida")})
public class RequisicaoItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RequisicaoItemPK requisicaoItemPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantidade_requerida")
    private Float quantidadeRequerida;
    @JoinColumn(name = "Requisicao_estoque_idRequisicao_estoque", referencedColumnName = "idRequisicao_estoque", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private RequisicaoEstoque requisicaoEstoque;
    @JoinColumn(name = "Item_idItem", referencedColumnName = "idItem", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Item item;

    public RequisicaoItem() {
    }

    public RequisicaoItem(RequisicaoItemPK requisicaoItemPK) {
        this.requisicaoItemPK = requisicaoItemPK;
    }

    public RequisicaoItem(int itemidItem, int requisicaoestoqueidRequisicaoestoque) {
        this.requisicaoItemPK = new RequisicaoItemPK(itemidItem, requisicaoestoqueidRequisicaoestoque);
    }

    public RequisicaoItemPK getRequisicaoItemPK() {
        return requisicaoItemPK;
    }

    public void setRequisicaoItemPK(RequisicaoItemPK requisicaoItemPK) {
        this.requisicaoItemPK = requisicaoItemPK;
    }

    public Float getQuantidadeRequerida() {
        return quantidadeRequerida;
    }

    public void setQuantidadeRequerida(Float quantidadeRequerida) {
        this.quantidadeRequerida = quantidadeRequerida;
    }

    public RequisicaoEstoque getRequisicaoEstoque() {
        return requisicaoEstoque;
    }

    public void setRequisicaoEstoque(RequisicaoEstoque requisicaoEstoque) {
        this.requisicaoEstoque = requisicaoEstoque;
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
        hash += (requisicaoItemPK != null ? requisicaoItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequisicaoItem)) {
            return false;
        }
        RequisicaoItem other = (RequisicaoItem) object;
        if ((this.requisicaoItemPK == null && other.requisicaoItemPK != null) || (this.requisicaoItemPK != null && !this.requisicaoItemPK.equals(other.requisicaoItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.RequisicaoItem[ requisicaoItemPK=" + requisicaoItemPK + " ]";
    }
    
}
