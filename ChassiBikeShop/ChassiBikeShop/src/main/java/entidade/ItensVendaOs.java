/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author lucas
 */
@Entity
public class ItensVendaOs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 100, nullable = false)
    private Double quantidade;
    @Column(length = 100, nullable = false)
    private Double valor;
    @ManyToOne
    private VendaOs vendaOs;
    @ManyToOne
    private ComposicaoProduto composicaoProduto;
    
    public ItensVendaOs(){
        quantidade = 0d;
        valor = 0d;
    }

    public ComposicaoProduto getComposicaoProduto() {
        return composicaoProduto;
    }

    public void setComposicaoProduto(ComposicaoProduto composicaoProduto) {
        this.composicaoProduto = composicaoProduto;
    }

    public VendaOs getVendaOs() {
        return vendaOs;
    }

    public void setVendaOs(VendaOs vendaOs) {
        this.vendaOs = vendaOs;
    }

    public Double getSubtotal() {
        return quantidade * valor;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

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
        if (!(object instanceof ItensVendaOs)) {
            return false;
        }
        ItensVendaOs other = (ItensVendaOs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ItensVendaOs[ id=" + id + " ]";
    }

}
