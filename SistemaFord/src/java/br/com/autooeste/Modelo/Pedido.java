/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Modelo;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
     @NamedQuery(name = "Pedido.lastReg", query = "select p from Pedido p where p.idPedido = (select max(ped.idPedido) from Pedido ped)"),
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByIdPedido", query = "SELECT p FROM Pedido p WHERE p.idPedido = :idPedido"),
    @NamedQuery(name = "Pedido.findByDataSolicitacao", query = "SELECT p FROM Pedido p WHERE p.dataSolicitacao = :dataSolicitacao"),
    @NamedQuery(name = "Pedido.findByAprovado", query = "SELECT p FROM Pedido p WHERE p.aprovado = :aprovado"),
    @NamedQuery(name = "Pedido.findByValor", query = "SELECT p FROM Pedido p WHERE p.valor = :valor"),
    @NamedQuery(name = "Pedido.findByJustificativa", query = "SELECT p FROM Pedido p WHERE p.justificativa = :justificativa")})
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPedido")
    private Integer idPedido;
    @Basic(optional = false)
    @Column(name = "data_solicitacao")
    private String dataSolicitacao;
    @Basic(optional = false)
    @Column(name = "aprovado")
    private int aprovado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @Column(name = "justificativa")
    private String justificativa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Collection<PedidoItem> pedidoItemCollection;
    @JoinColumn(name = "nf_entrada_id", referencedColumnName = "idnf_entrada")
    @ManyToOne
    private NfEntrada nfEntradaId;
    @JoinColumn(name = "Fornecedor_idFornecedor", referencedColumnName = "idFornecedor")
    @ManyToOne
    private Fornecedor fornecedoridFornecedor;
    @JoinColumn(name = "Funcionario_idFuncionario", referencedColumnName = "idFuncionario")
    @ManyToOne(optional = false)
    private Funcionario funcionarioidFuncionario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoidPedido")
    private Collection<Cotacao> cotacaoCollection;

    public Pedido() {
    }

    public Pedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Pedido(Integer idPedido, String dataSolicitacao, int aprovado) {
        this.idPedido = idPedido;
        this.dataSolicitacao = dataSolicitacao;
        this.aprovado = aprovado;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(String dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public int getAprovado() {
        return aprovado;
    }

    public void setAprovado(int aprovado) {
        this.aprovado = aprovado;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    @XmlTransient
    public Collection<PedidoItem> getPedidoItemCollection() {
        return pedidoItemCollection;
    }

    public void setPedidoItemCollection(Collection<PedidoItem> pedidoItemCollection) {
        this.pedidoItemCollection = pedidoItemCollection;
    }

    public NfEntrada getNfEntradaId() {
        return nfEntradaId;
    }

    public void setNfEntradaId(NfEntrada nfEntradaId) {
        this.nfEntradaId = nfEntradaId;
    }

    public Fornecedor getFornecedoridFornecedor() {
        return fornecedoridFornecedor;
    }

    public void setFornecedoridFornecedor(Fornecedor fornecedoridFornecedor) {
        this.fornecedoridFornecedor = fornecedoridFornecedor;
    }

    public Funcionario getFuncionarioidFuncionario() {
        return funcionarioidFuncionario;
    }

    public void setFuncionarioidFuncionario(Funcionario funcionarioidFuncionario) {
        this.funcionarioidFuncionario = funcionarioidFuncionario;
    }

    @XmlTransient
    public Collection<Cotacao> getCotacaoCollection() {
        return cotacaoCollection;
    }

    public void setCotacaoCollection(Collection<Cotacao> cotacaoCollection) {
        this.cotacaoCollection = cotacaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.Pedido[ idPedido=" + idPedido + " ]";
    }
    
}
