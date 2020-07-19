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
public class ItensAjusteEstoque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 100, nullable = false)
    private Double estoqueAnterior;
    @Column(length = 100, nullable = false)
    private Double estoqueAtual;
    @ManyToOne
    private AjusteEstoque ajusteEstoque;
    @ManyToOne
    private ComposicaoProduto composicaoProduto;

    public Double getEstoqueAnterior() {
        return estoqueAnterior;
    }

    public void setEstoqueAnterior(Double estoqueAnterior) {
        this.estoqueAnterior = estoqueAnterior;
    }

    public Double getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(Double estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public AjusteEstoque getAjusteEstoque() {
        return ajusteEstoque;
    }

    public void setAjusteEstoque(AjusteEstoque ajusteEstoque) {
        this.ajusteEstoque = ajusteEstoque;
    }

    public ComposicaoProduto getComposicaoProduto() {
        return composicaoProduto;
    }

    public void setComposicaoProduto(ComposicaoProduto composicaoProduto) {
        this.composicaoProduto = composicaoProduto;
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
        if (!(object instanceof ItensAjusteEstoque)) {
            return false;
        }
        ItensAjusteEstoque other = (ItensAjusteEstoque) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ItensAjusteEstoque[ id=" + id + " ]";
    }

}
