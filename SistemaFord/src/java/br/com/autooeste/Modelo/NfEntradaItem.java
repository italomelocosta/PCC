/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "nf_entrada_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NfEntradaItem.findAll", query = "SELECT n FROM NfEntradaItem n"),
    @NamedQuery(name = "NfEntradaItem.findByNfEntradaIdnfEntrada", query = "SELECT n FROM NfEntradaItem n WHERE n.nfEntradaItemPK.nfEntradaIdnfEntrada = :nfEntradaIdnfEntrada"),
    @NamedQuery(name = "NfEntradaItem.findByItemidItem", query = "SELECT n FROM NfEntradaItem n WHERE n.nfEntradaItemPK.itemidItem = :itemidItem"),
    @NamedQuery(name = "NfEntradaItem.findByQuantidadeComprada", query = "SELECT n FROM NfEntradaItem n WHERE n.quantidadeComprada = :quantidadeComprada"),
    @NamedQuery(name = "NfEntradaItem.findByValor", query = "SELECT n FROM NfEntradaItem n WHERE n.valor = :valor")})
public class NfEntradaItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NfEntradaItemPK nfEntradaItemPK;
    @Basic(optional = false)
    @Column(name = "quantidade_comprada")
    private float quantidadeComprada;
    @Basic(optional = false)
    @Column(name = "valor")
    private float valor;
    @JoinColumn(name = "Item_idItem", referencedColumnName = "idItem", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Item item;
    @JoinColumn(name = "nf_entrada_idnf_entrada", referencedColumnName = "idnf_entrada", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private NfEntrada nfEntrada;

    public NfEntradaItem() {
    }

    public NfEntradaItem(NfEntradaItemPK nfEntradaItemPK) {
        this.nfEntradaItemPK = nfEntradaItemPK;
    }

    public NfEntradaItem(NfEntradaItemPK nfEntradaItemPK, float quantidadeComprada, float valor) {
        this.nfEntradaItemPK = nfEntradaItemPK;
        this.quantidadeComprada = quantidadeComprada;
        this.valor = valor;
    }

    public NfEntradaItem(int nfEntradaIdnfEntrada, int itemidItem) {
        this.nfEntradaItemPK = new NfEntradaItemPK(nfEntradaIdnfEntrada, itemidItem);
    }

    public NfEntradaItemPK getNfEntradaItemPK() {
        return nfEntradaItemPK;
    }

    public void setNfEntradaItemPK(NfEntradaItemPK nfEntradaItemPK) {
        this.nfEntradaItemPK = nfEntradaItemPK;
    }

    public float getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(float quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public NfEntrada getNfEntrada() {
        return nfEntrada;
    }

    public void setNfEntrada(NfEntrada nfEntrada) {
        this.nfEntrada = nfEntrada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nfEntradaItemPK != null ? nfEntradaItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NfEntradaItem)) {
            return false;
        }
        NfEntradaItem other = (NfEntradaItem) object;
        if ((this.nfEntradaItemPK == null && other.nfEntradaItemPK != null) || (this.nfEntradaItemPK != null && !this.nfEntradaItemPK.equals(other.nfEntradaItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.NfEntradaItem[ nfEntradaItemPK=" + nfEntradaItemPK + " ]";
    }
    
}
