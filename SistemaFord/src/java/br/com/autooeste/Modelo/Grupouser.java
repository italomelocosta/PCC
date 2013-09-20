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
@Table(name = "grupouser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupouser.findAll", query = "SELECT g FROM Grupouser g"),
    @NamedQuery(name = "Grupouser.findByIdGrupoUser", query = "SELECT g FROM Grupouser g WHERE g.idGrupoUser = :idGrupoUser"),
    @NamedQuery(name = "Grupouser.findByNomeGrupo", query = "SELECT g FROM Grupouser g WHERE g.nomeGrupo = :nomeGrupo")})
public class Grupouser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGrupoUser")
    private Integer idGrupoUser;
    @Basic(optional = false)
    @Column(name = "nomeGrupo")
    private String nomeGrupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoUseridGrupoUser")
    private Collection<Funcionario> funcionarioCollection;

    public Grupouser() {
    }

    public Grupouser(Integer idGrupoUser) {
        this.idGrupoUser = idGrupoUser;
    }

    public Grupouser(Integer idGrupoUser, String nomeGrupo) {
        this.idGrupoUser = idGrupoUser;
        this.nomeGrupo = nomeGrupo;
    }

    public Integer getIdGrupoUser() {
        return idGrupoUser;
    }

    public void setIdGrupoUser(Integer idGrupoUser) {
        this.idGrupoUser = idGrupoUser;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    @XmlTransient
    public Collection<Funcionario> getFuncionarioCollection() {
        return funcionarioCollection;
    }

    public void setFuncionarioCollection(Collection<Funcionario> funcionarioCollection) {
        this.funcionarioCollection = funcionarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoUser != null ? idGrupoUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupouser)) {
            return false;
        }
        Grupouser other = (Grupouser) object;
        if ((this.idGrupoUser == null && other.idGrupoUser != null) || (this.idGrupoUser != null && !this.idGrupoUser.equals(other.idGrupoUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.autooeste.Modelo.Grupouser[ idGrupoUser=" + idGrupoUser + " ]";
    }
    
}
