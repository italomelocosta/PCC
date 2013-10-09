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
@Table(name = "nf_entrada_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NfEntradaItem.findAll", query = "SELECT n FROM NfEntradaItem n"),
    @NamedQuery(name = "NfEntradaItem.findByIdNfEntrada", query = "SELECT n FROM NfEntradaItem n WHERE n.idNfEntrada = :idNfEntrada"),
    @NamedQuery(name = "NfEntradaItem.findByQuantidadeComprada", query = "SELECT n FROM NfEntradaItem n WHERE n.quantidadeComprada = :quantidadeComprada"),
    @NamedQuery(name = "NfEntradaItem.findByValor", query = "SELECT n FROM NfEntradaItem n WHERE n.valor = :valor")})
public class NfEntradaItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNfEntrada")
    private Integer idNfEntrada;
    @Basic(optional = false)
    @Column(name = "quantidade_comprada")
    private float quantidadeComprada;
    @Basic(optional = false)
    @Column(name = "valor")
    private float valor;
    @JoinColumn(name = "Item_idItem", referencedColumnName = "idItem")
    @ManyToOne(optional = false)
    private Item itemidItem;
    @JoinColumn(name = "nf_entrada_idnf_entrada", referencedColumnName = "idnf_entrada")
    @ManyToOne(optional = false)
    private NfEntrada nfEntradaIdnfEntrada;

    public NfEntradaItem() {
    }

    public NfEntradaItem(Integer idNfEntrada) {
        this.idNfEntrada = idNfEntrada;
    }

    public NfEntradaItem(Integer idNfEntrada, float quantidadeComprada, float valor) {
        this.idNfEntrada = idNfEntrada;
        this.quantidadeComprada = quantidadeComprada;
        this.valor = valor;
    }

    public Integer getIdNfEntrada() {
        return idNfEntrada;
    }

    public void setIdNfEntrada(Integer idNfEntrada) {
        this.idNfEntrada = idNfEntrada;
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

    public Item getItemidItem() {
        return itemidItem;
    }

    public void setItemidItem(Item itemidItem) {
        this.itemidItem = itemidItem;
    }

    public NfEntrada getNfEntradaIdnfEntrada() {
        return nfEntradaIdnfEntrada;
    }

    public void setNfEntradaIdnfEntrada(NfEntrada nfEntradaIdnfEntrada) {
        this.nfEntradaIdnfEntrada = nfEntradaIdnfEntrada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNfEntrada != null ? idNfEntrada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NfEntradaItem)) {
            return false;
        }
        NfEntradaItem other = (NfEntradaItem) object;
        if ((this.idNfEntrada == null && other.idNfEntrada != null) || (this.idNfEntrada != null && !this.idNfEntrada.equals(other.idNfEntrada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.NfEntradaItem[ idNfEntrada=" + idNfEntrada + " ]";
    }
    
}
