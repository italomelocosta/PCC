/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Italo
 */
@Entity
@Table(name = "nf_entrada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NfEntrada.findAll", query = "SELECT n FROM NfEntrada n"),
    @NamedQuery(name = "NfEntrada.findByIdnfEntrada", query = "SELECT n FROM NfEntrada n WHERE n.idnfEntrada = :idnfEntrada"),
    @NamedQuery(name = "NfEntrada.findByDataEmissao", query = "SELECT n FROM NfEntrada n WHERE n.dataEmissao = :dataEmissao"),
    @NamedQuery(name = "NfEntrada.findByDataEntrada", query = "SELECT n FROM NfEntrada n WHERE n.dataEntrada = :dataEntrada"),
    @NamedQuery(name = "NfEntrada.findByValorTotal", query = "SELECT n FROM NfEntrada n WHERE n.valorTotal = :valorTotal")})
public class NfEntrada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idnf_entrada")
    private Integer idnfEntrada;
    @Column(name = "data_emissao")
    @Temporal(TemporalType.DATE)
    private Date dataEmissao;
    @Column(name = "data_entrada")
    @Temporal(TemporalType.DATE)
    private Date dataEntrada;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_total")
    private Float valorTotal;
    @JoinColumn(name = "Fornecedor_idFornecedor", referencedColumnName = "idFornecedor")
    @ManyToOne(optional = false)
    private Fornecedor fornecedoridFornecedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nfEntrada")
    private List<NfEntradaItem> nfEntradaItemList;
    @OneToMany(mappedBy = "nfEntradaId")
    private List<Pedido> pedidoList;

    public NfEntrada() {
    }

    public NfEntrada(Integer idnfEntrada) {
        this.idnfEntrada = idnfEntrada;
    }

    public Integer getIdnfEntrada() {
        return idnfEntrada;
    }

    public void setIdnfEntrada(Integer idnfEntrada) {
        this.idnfEntrada = idnfEntrada;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Fornecedor getFornecedoridFornecedor() {
        return fornecedoridFornecedor;
    }

    public void setFornecedoridFornecedor(Fornecedor fornecedoridFornecedor) {
        this.fornecedoridFornecedor = fornecedoridFornecedor;
    }

    @XmlTransient
    public List<NfEntradaItem> getNfEntradaItemList() {
        return nfEntradaItemList;
    }

    public void setNfEntradaItemList(List<NfEntradaItem> nfEntradaItemList) {
        this.nfEntradaItemList = nfEntradaItemList;
    }

    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnfEntrada != null ? idnfEntrada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NfEntrada)) {
            return false;
        }
        NfEntrada other = (NfEntrada) object;
        if ((this.idnfEntrada == null && other.idnfEntrada != null) || (this.idnfEntrada != null && !this.idnfEntrada.equals(other.idnfEntrada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.NfEntrada[ idnfEntrada=" + idnfEntrada + " ]";
    }
    
}
