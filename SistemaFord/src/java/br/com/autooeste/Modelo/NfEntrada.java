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
@Table(name = "nf_entrada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NfEntrada.lastReg", query = "SELECT n FROM NfEntrada n where n.idnfEntrada = (select max(nf.idnfEntrada) from NfEntrada nf)"),
    @NamedQuery(name = "NfEntrada.findAll", query = "SELECT n FROM NfEntrada n"),
    @NamedQuery(name = "NfEntrada.findByIdnfEntrada", query = "SELECT n FROM NfEntrada n WHERE n.idnfEntrada = :idnfEntrada"),
    @NamedQuery(name = "NfEntrada.findByDataEmissao", query = "SELECT n FROM NfEntrada n WHERE n.dataEmissao = :dataEmissao"),
    @NamedQuery(name = "NfEntrada.findByDataEntrada", query = "SELECT n FROM NfEntrada n WHERE n.dataEntrada = :dataEntrada"),
    @NamedQuery(name = "NfEntrada.findByValorTotal", query = "SELECT n FROM NfEntrada n WHERE n.valorTotal = :valorTotal")})
public class NfEntrada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnf_entrada")
    private Integer idnfEntrada;
    @Column(name = "data_emissao")
    private String dataEmissao;
    @Column(name = "data_entrada")
    private String dataEntrada;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_total")
    private Float valorTotal;
    @JoinColumn(name = "Fornecedor_idFornecedor", referencedColumnName = "idFornecedor")
    @ManyToOne
    private Fornecedor fornecedoridFornecedor;

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

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
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
