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
@Table(name = "requisicao_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RequisicaoItem.findAll", query = "SELECT r FROM RequisicaoItem r"),
    @NamedQuery(name = "RequisicaoItem.findByIdReqItem", query = "SELECT r FROM RequisicaoItem r WHERE r.idReqItem = :idReqItem"),
    @NamedQuery(name = "RequisicaoItem.findByQuantidadeRequerida", query = "SELECT r FROM RequisicaoItem r WHERE r.quantidadeRequerida = :quantidadeRequerida")})
public class RequisicaoItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReqItem")
    private Integer idReqItem;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantidade_requerida")
    private Float quantidadeRequerida;
    @JoinColumn(name = "Requisicao_estoque_idRequisicao_estoque", referencedColumnName = "idRequisicao_estoque")
    @ManyToOne(optional = false)
    private RequisicaoEstoque requisicaoestoqueidRequisicaoestoque;
    @JoinColumn(name = "Item_idItem", referencedColumnName = "idItem")
    @ManyToOne(optional = false)
    private Item itemidItem;

    public RequisicaoItem() {
    }

    public RequisicaoItem(Integer idReqItem) {
        this.idReqItem = idReqItem;
    }

    public Integer getIdReqItem() {
        return idReqItem;
    }

    public void setIdReqItem(Integer idReqItem) {
        this.idReqItem = idReqItem;
    }

    public Float getQuantidadeRequerida() {
        return quantidadeRequerida;
    }

    public void setQuantidadeRequerida(Float quantidadeRequerida) {
        this.quantidadeRequerida = quantidadeRequerida;
    }

    public RequisicaoEstoque getRequisicaoestoqueidRequisicaoestoque() {
        return requisicaoestoqueidRequisicaoestoque;
    }

    public void setRequisicaoestoqueidRequisicaoestoque(RequisicaoEstoque requisicaoestoqueidRequisicaoestoque) {
        this.requisicaoestoqueidRequisicaoestoque = requisicaoestoqueidRequisicaoestoque;
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
        hash += (idReqItem != null ? idReqItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequisicaoItem)) {
            return false;
        }
        RequisicaoItem other = (RequisicaoItem) object;
        if ((this.idReqItem == null && other.idReqItem != null) || (this.idReqItem != null && !this.idReqItem.equals(other.idReqItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.RequisicaoItem[ idReqItem=" + idReqItem + " ]";
    }
    
}
