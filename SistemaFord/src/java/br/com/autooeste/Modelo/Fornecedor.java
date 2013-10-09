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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Italo
 */
@Entity
@Table(name = "fornecedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f"),
    @NamedQuery(name = "Fornecedor.findByIdFornecedor", query = "SELECT f FROM Fornecedor f WHERE f.idFornecedor = :idFornecedor"),
    @NamedQuery(name = "Fornecedor.findByRazaosocial", query = "SELECT f FROM Fornecedor f WHERE f.razaosocial = :razaosocial"),
    @NamedQuery(name = "Fornecedor.findByCnpj", query = "SELECT f FROM Fornecedor f WHERE f.cnpj = :cnpj"),
    @NamedQuery(name = "Fornecedor.findByTelefone", query = "SELECT f FROM Fornecedor f WHERE f.telefone = :telefone")})
public class Fornecedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFornecedor")
    private Integer idFornecedor;
    @Basic(optional = false)
    @Column(name = "razaosocial")
    private String razaosocial;
    @Basic(optional = false)
    @Column(name = "cnpj")
    private String cnpj;
    @Basic(optional = false)
    @Column(name = "telefone")
    private String telefone;
    @JoinColumn(name = "Endereco_idEndereco", referencedColumnName = "idEndereco")
    @ManyToOne(optional = false)
    private Endereco enderecoidEndereco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedoridFornecedor")
    private List<NfEntrada> nfEntradaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedoridFornecedor")
    private List<Estoque> estoqueList;
    @OneToMany(mappedBy = "fornecedoridFornecedor")
    private List<Pedido> pedidoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedoridFornecedor")
    private List<Cotacao> cotacaoList;

    public Fornecedor() {
    }

    public Fornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Fornecedor(Integer idFornecedor, String razaosocial, String cnpj, String telefone) {
        this.idFornecedor = idFornecedor;
        this.razaosocial = razaosocial;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEnderecoidEndereco() {
        return enderecoidEndereco;
    }

    public void setEnderecoidEndereco(Endereco enderecoidEndereco) {
        this.enderecoidEndereco = enderecoidEndereco;
    }

    @XmlTransient
    public List<NfEntrada> getNfEntradaList() {
        return nfEntradaList;
    }

    public void setNfEntradaList(List<NfEntrada> nfEntradaList) {
        this.nfEntradaList = nfEntradaList;
    }

    @XmlTransient
    public List<Estoque> getEstoqueList() {
        return estoqueList;
    }

    public void setEstoqueList(List<Estoque> estoqueList) {
        this.estoqueList = estoqueList;
    }

    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @XmlTransient
    public List<Cotacao> getCotacaoList() {
        return cotacaoList;
    }

    public void setCotacaoList(List<Cotacao> cotacaoList) {
        this.cotacaoList = cotacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFornecedor != null ? idFornecedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.idFornecedor == null && other.idFornecedor != null) || (this.idFornecedor != null && !this.idFornecedor.equals(other.idFornecedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.Fornecedor[ idFornecedor=" + idFornecedor + " ]";
    }
    
}
