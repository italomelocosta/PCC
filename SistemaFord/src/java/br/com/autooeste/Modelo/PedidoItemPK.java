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
public class PedidoItemPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "Pedido_idPedido")
    private int pedidoidPedido;
    @Basic(optional = false)
    @Column(name = "Item_idItem")
    private int itemidItem;

    public PedidoItemPK() {
    }

    public PedidoItemPK(int pedidoidPedido, int itemidItem) {
        this.pedidoidPedido = pedidoidPedido;
        this.itemidItem = itemidItem;
    }

    public int getPedidoidPedido() {
        return pedidoidPedido;
    }

    public void setPedidoidPedido(int pedidoidPedido) {
        this.pedidoidPedido = pedidoidPedido;
    }

    public int getItemidItem() {
        return itemidItem;
    }

    public void setItemidItem(int itemidItem) {
        this.itemidItem = itemidItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pedidoidPedido;
        hash += (int) itemidItem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoItemPK)) {
            return false;
        }
        PedidoItemPK other = (PedidoItemPK) object;
        if (this.pedidoidPedido != other.pedidoidPedido) {
            return false;
        }
        if (this.itemidItem != other.itemidItem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.PedidoItemPK[ pedidoidPedido=" + pedidoidPedido + ", itemidItem=" + itemidItem + " ]";
    }
    
}
