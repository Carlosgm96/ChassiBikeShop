/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author lucas
 */
@Entity
public class VendaOs implements Serializable, EntidadePai {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataVendaOs;
    private String formaPag;
    private Double valorTotal;
    @ManyToOne
    private Pessoa pessoa;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "vendaOs")
    private List<ContasReceber> contasRecebers;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "vendaOs")
    private List<ItensVendaOs> itensVendaOs;
    

    public VendaOs() {
        this.dataVendaOs = new Date();
        this.itensVendaOs = new ArrayList<ItensVendaOs>();
        this.contasRecebers = new ArrayList<ContasReceber>();
    }

    public Date getDataVendaOs() {
        return dataVendaOs;
    }

    public void setDataVendaOs(Date dataVendaOs) {
        this.dataVendaOs = dataVendaOs;
    }

    public String getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(String formaPag) {
        this.formaPag = formaPag;
    }

    public Double getValorTotal() {
        valorTotal = 0d;
        for (ItensVendaOs is : itensVendaOs) {
            valorTotal += is.getSubtotal();
        }
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<ContasReceber> getContasRecebers() {
        return contasRecebers;
    }

    public void setContasRecebers(List<ContasReceber> contasRecebers) {
        this.contasRecebers = contasRecebers;
    }

    public List<ItensVendaOs> getItensVendaOs() {
        return itensVendaOs;
    }

    public void setItensVendaOs(List<ItensVendaOs> itensVendaOs) {
        this.itensVendaOs = itensVendaOs;
    }
    
    

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendaOs)) {
            return false;
        }
        VendaOs other = (VendaOs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.VendaOs[ id=" + id + " ]";
    }

}
