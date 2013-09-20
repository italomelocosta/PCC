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
@Table(name = "pedido_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidoItem.findAll", query = "SELECT p FROM PedidoItem p"),
    @NamedQuery(name = "PedidoItem.findByPedidoidPedido", query = "SELECT p FROM PedidoItem p WHERE p.pedidoItemPK.pedidoidPedido = :pedidoidPedido"),
    @NamedQuery(name = "PedidoItem.findByItemidItem", query = "SELECT p FROM PedidoItem p WHERE p.pedidoItemPK.itemidItem = :itemidItem"),
    @NamedQuery(name = "PedidoItem.findByQuantidadePedido", query = "SELECT p FROM PedidoItem p WHERE p.quantidadePedido = :quantidadePedido"),
    @NamedQuery(name = "PedidoItem.findByValorUnitarioPedido", query = "SELECT p FROM PedidoItem p WHERE p.valorUnitarioPedido = :valorUnitarioPedido")})
public class PedidoItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PedidoItemPK pedidoItemPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantidade_pedido")
    private Float quantidadePedido;
    @Column(name = "valor_unitario_pedido")
    private Float valorUnitarioPedido;
    @JoinColumn(name = "Item_idItem", referencedColumnName = "idItem", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Item item;
    @JoinColumn(name = "Pedido_idPedido", referencedColumnName = "idPedido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;

    public PedidoItem() {
    }

    public PedidoItem(PedidoItemPK pedidoItemPK) {
        this.pedidoItemPK = pedidoItemPK;
    }

    public PedidoItem(int pedidoidPedido, int itemidItem) {
        this.pedidoItemPK = new PedidoItemPK(pedidoidPedido, itemidItem);
    }

    public PedidoItemPK getPedidoItemPK() {
        return pedidoItemPK;
    }

    public void setPedidoItemPK(PedidoItemPK pedidoItemPK) {
        this.pedidoItemPK = pedidoItemPK;
    }

    public Float getQuantidadePedido() {
        return quantidadePedido;
    }

    public void setQuantidadePedido(Float quantidadePedido) {
        this.quantidadePedido = quantidadePedido;
    }

    public Float getValorUnitarioPedido() {
        return valorUnitarioPedido;
    }

    public void setValorUnitarioPedido(Float valorUnitarioPedido) {
        this.valorUnitarioPedido = valorUnitarioPedido;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidoItemPK != null ? pedidoItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoItem)) {
            return false;
        }
        PedidoItem other = (PedidoItem) object;
        if ((this.pedidoItemPK == null && other.pedidoItemPK != null) || (this.pedidoItemPK != null && !this.pedidoItemPK.equals(other.pedidoItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.PedidoItem[ pedidoItemPK=" + pedidoItemPK + " ]";
    }
    
}
