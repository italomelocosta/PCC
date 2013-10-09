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
@Table(name = "pedido_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidoItem.findAll", query = "SELECT p FROM PedidoItem p"),
    @NamedQuery(name = "PedidoItem.findByIdPedItem", query = "SELECT p FROM PedidoItem p WHERE p.idPedItem = :idPedItem"),
    @NamedQuery(name = "PedidoItem.findByQuantidadePedido", query = "SELECT p FROM PedidoItem p WHERE p.quantidadePedido = :quantidadePedido"),
    @NamedQuery(name = "PedidoItem.findByValorUnitarioPedido", query = "SELECT p FROM PedidoItem p WHERE p.valorUnitarioPedido = :valorUnitarioPedido")})
public class PedidoItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPedItem")
    private Integer idPedItem;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantidade_pedido")
    private Float quantidadePedido;
    @Column(name = "valor_unitario_pedido")
    private Float valorUnitarioPedido;
    @JoinColumn(name = "Item_idItem", referencedColumnName = "idItem")
    @ManyToOne(optional = false)
    private Item itemidItem;
    @JoinColumn(name = "Pedido_idPedido", referencedColumnName = "idPedido")
    @ManyToOne(optional = false)
    private Pedido pedidoidPedido;

    public PedidoItem() {
    }

    public PedidoItem(Integer idPedItem) {
        this.idPedItem = idPedItem;
    }

    public Integer getIdPedItem() {
        return idPedItem;
    }

    public void setIdPedItem(Integer idPedItem) {
        this.idPedItem = idPedItem;
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
        hash += (idPedItem != null ? idPedItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoItem)) {
            return false;
        }
        PedidoItem other = (PedidoItem) object;
        if ((this.idPedItem == null && other.idPedItem != null) || (this.idPedItem != null && !this.idPedItem.equals(other.idPedItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.PedidoItem[ idPedItem=" + idPedItem + " ]";
    }
    
}
