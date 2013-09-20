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
@Table(name = "requisicao_estoque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RequisicaoEstoque.findAll", query = "SELECT r FROM RequisicaoEstoque r"),
    @NamedQuery(name = "RequisicaoEstoque.findByIdRequisicaoestoque", query = "SELECT r FROM RequisicaoEstoque r WHERE r.idRequisicaoestoque = :idRequisicaoestoque"),
    @NamedQuery(name = "RequisicaoEstoque.findByDtRequisicao", query = "SELECT r FROM RequisicaoEstoque r WHERE r.dtRequisicao = :dtRequisicao"),
    @NamedQuery(name = "RequisicaoEstoque.findByMotSolicitacao", query = "SELECT r FROM RequisicaoEstoque r WHERE r.motSolicitacao = :motSolicitacao")})
public class RequisicaoEstoque implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRequisicao_estoque")
    private Integer idRequisicaoestoque;
    @Basic(optional = false)
    @Column(name = "dt_requisicao")
    private String dtRequisicao;
    @Basic(optional = false)
    @Column(name = "mot_solicitacao")
    private String motSolicitacao;
    @JoinColumn(name = "Funcionario_idFuncionario", referencedColumnName = "idFuncionario")
    @ManyToOne(optional = false)
    private Funcionario funcionarioidFuncionario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requisicaoEstoque")
    private Collection<RequisicaoItem> requisicaoItemCollection;

    public RequisicaoEstoque() {
    }

    public RequisicaoEstoque(Integer idRequisicaoestoque) {
        this.idRequisicaoestoque = idRequisicaoestoque;
    }

    public RequisicaoEstoque(Integer idRequisicaoestoque, String dtRequisicao, String motSolicitacao) {
        this.idRequisicaoestoque = idRequisicaoestoque;
        this.dtRequisicao = dtRequisicao;
        this.motSolicitacao = motSolicitacao;
    }

    public Integer getIdRequisicaoestoque() {
        return idRequisicaoestoque;
    }

    public void setIdRequisicaoestoque(Integer idRequisicaoestoque) {
        this.idRequisicaoestoque = idRequisicaoestoque;
    }

    public String getDtRequisicao() {
        return dtRequisicao;
    }

    public void setDtRequisicao(String dtRequisicao) {
        this.dtRequisicao = dtRequisicao;
    }

    public String getMotSolicitacao() {
        return motSolicitacao;
    }

    public void setMotSolicitacao(String motSolicitacao) {
        this.motSolicitacao = motSolicitacao;
    }

    public Funcionario getFuncionarioidFuncionario() {
        return funcionarioidFuncionario;
    }

    public void setFuncionarioidFuncionario(Funcionario funcionarioidFuncionario) {
        this.funcionarioidFuncionario = funcionarioidFuncionario;
    }

    @XmlTransient
    public Collection<RequisicaoItem> getRequisicaoItemCollection() {
        return requisicaoItemCollection;
    }

    public void setRequisicaoItemCollection(Collection<RequisicaoItem> requisicaoItemCollection) {
        this.requisicaoItemCollection = requisicaoItemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRequisicaoestoque != null ? idRequisicaoestoque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequisicaoEstoque)) {
            return false;
        }
        RequisicaoEstoque other = (RequisicaoEstoque) object;
        if ((this.idRequisicaoestoque == null && other.idRequisicaoestoque != null) || (this.idRequisicaoestoque != null && !this.idRequisicaoestoque.equals(other.idRequisicaoestoque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.RequisicaoEstoque[ idRequisicaoestoque=" + idRequisicaoestoque + " ]";
    }
    
}
