/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Italo
 */
@Entity
@Table(name = "item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByIdItem", query = "SELECT i FROM Item i WHERE i.idItem = :idItem"),
    @NamedQuery(name = "Item.findByDescricao", query = "SELECT i FROM Item i WHERE i.descricao = :descricao"),
    @NamedQuery(name = "Item.findByMedida", query = "SELECT i FROM Item i WHERE i.medida = :medida")})
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idItem")
    private Integer idItem;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "medida")
    private String medida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<PedidoItem> pedidoItemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<NfEntradaItem> nfEntradaItemList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "item")
    private Estoque estoque;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemidItem")
    private List<Cotacao> cotacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<RequisicaoItem> requisicaoItemList;

    public Item() {
    }

    public Item(Integer idItem) {
        this.idItem = idItem;
    }

    public Item(Integer idItem, String descricao, String medida) {
        this.idItem = idItem;
        this.descricao = descricao;
        this.medida = medida;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    @XmlTransient
    public List<PedidoItem> getPedidoItemList() {
        return pedidoItemList;
    }

    public void setPedidoItemList(List<PedidoItem> pedidoItemList) {
        this.pedidoItemList = pedidoItemList;
    }

    @XmlTransient
    public List<NfEntradaItem> getNfEntradaItemList() {
        return nfEntradaItemList;
    }

    public void setNfEntradaItemList(List<NfEntradaItem> nfEntradaItemList) {
        this.nfEntradaItemList = nfEntradaItemList;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    @XmlTransient
    public List<Cotacao> getCotacaoList() {
        return cotacaoList;
    }

    public void setCotacaoList(List<Cotacao> cotacaoList) {
        this.cotacaoList = cotacaoList;
    }

    @XmlTransient
    public List<RequisicaoItem> getRequisicaoItemList() {
        return requisicaoItemList;
    }

    public void setRequisicaoItemList(List<RequisicaoItem> requisicaoItemList) {
        this.requisicaoItemList = requisicaoItemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.idItem == null && other.idItem != null) || (this.idItem != null && !this.idItem.equals(other.idItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.Item[ idItem=" + idItem + " ]";
    }
    
}
