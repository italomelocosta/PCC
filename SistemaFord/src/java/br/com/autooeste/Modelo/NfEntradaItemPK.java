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
public class NfEntradaItemPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "nf_entrada_idnf_entrada")
    private int nfEntradaIdnfEntrada;
    @Basic(optional = false)
    @Column(name = "Item_idItem")
    private int itemidItem;

    public NfEntradaItemPK() {
    }

    public NfEntradaItemPK(int nfEntradaIdnfEntrada, int itemidItem) {
        this.nfEntradaIdnfEntrada = nfEntradaIdnfEntrada;
        this.itemidItem = itemidItem;
    }

    public int getNfEntradaIdnfEntrada() {
        return nfEntradaIdnfEntrada;
    }

    public void setNfEntradaIdnfEntrada(int nfEntradaIdnfEntrada) {
        this.nfEntradaIdnfEntrada = nfEntradaIdnfEntrada;
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
        hash += (int) nfEntradaIdnfEntrada;
        hash += (int) itemidItem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NfEntradaItemPK)) {
            return false;
        }
        NfEntradaItemPK other = (NfEntradaItemPK) object;
        if (this.nfEntradaIdnfEntrada != other.nfEntradaIdnfEntrada) {
            return false;
        }
        if (this.itemidItem != other.itemidItem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.NfEntradaItemPK[ nfEntradaIdnfEntrada=" + nfEntradaIdnfEntrada + ", itemidItem=" + itemidItem + " ]";
    }
    
}
