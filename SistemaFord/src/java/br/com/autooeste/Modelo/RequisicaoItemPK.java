/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Italo
 */
@Embeddable
public class RequisicaoItemPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "Item_idItem")
    private int itemidItem;
    @Basic(optional = false)
    @Column(name = "Requisicao_estoque_idRequisicao_estoque")
    private int requisicaoestoqueidRequisicaoestoque;

    public RequisicaoItemPK() {
    }

    public RequisicaoItemPK(int itemidItem, int requisicaoestoqueidRequisicaoestoque) {
        this.itemidItem = itemidItem;
        this.requisicaoestoqueidRequisicaoestoque = requisicaoestoqueidRequisicaoestoque;
    }

    public int getItemidItem() {
        return itemidItem;
    }

    public void setItemidItem(int itemidItem) {
        this.itemidItem = itemidItem;
    }

    public int getRequisicaoestoqueidRequisicaoestoque() {
        return requisicaoestoqueidRequisicaoestoque;
    }

    public void setRequisicaoestoqueidRequisicaoestoque(int requisicaoestoqueidRequisicaoestoque) {
        this.requisicaoestoqueidRequisicaoestoque = requisicaoestoqueidRequisicaoestoque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) itemidItem;
        hash += (int) requisicaoestoqueidRequisicaoestoque;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequisicaoItemPK)) {
            return false;
        }
        RequisicaoItemPK other = (RequisicaoItemPK) object;
        if (this.itemidItem != other.itemidItem) {
            return false;
        }
        if (this.requisicaoestoqueidRequisicaoestoque != other.requisicaoestoqueidRequisicaoestoque) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.RequisicaoItemPK[ itemidItem=" + itemidItem + ", requisicaoestoqueidRequisicaoestoque=" + requisicaoestoqueidRequisicaoestoque + " ]";
    }
    
}
