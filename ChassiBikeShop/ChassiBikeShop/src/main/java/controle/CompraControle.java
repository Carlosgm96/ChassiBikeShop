/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.BaixaContasPagar;
import entidade.PessoaJuridica;
import entidade.ComposicaoProduto;
import entidade.ContasPagar;
import entidade.ItensCompra;
import entidade.Compra;
import entidade.Produto;
import facade.PessoaJuridicaFacade;
import facade.CompraFacade;
import facade.ProdutoFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

/**
 *
 * @author carol
 */
@Named
@ViewAccessScoped
public class CompraControle implements Serializable {

    @Inject
    private CompraFacade compraFacade;
    private Compra compra;
    private ItensCompra itensCompra;
    @Inject
    private PessoaJuridicaFacade pessoaJuridicaFacade;
    @Inject
    private ProdutoFacade produtoFacade;
    private ConverterGenerico converterPessoaJuridica;
    private ConverterGenerico converterComposicaoProduto;
    private ConverterGenerico converterProduto;
    private MoneyConverter moneyConverter;
    private Integer numParcela;
    private Date dtVencimento;
    private ContasPagar contasPagar;
    private PessoaJuridica pessoaJuridica;
    private Produto produto;
    private String teste = "old";
    private List<ComposicaoProduto> composicaoProduto;
    
        public String getSituacao(){
            return "Baixado";
    }

    public void setarComposicao(ComposicaoProduto cp) {
        if (itensCompra.getQuantidade().equals(0d)) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Obrigatório adicionar a quantidade de produto",
                            ""));
        } else {

            itensCompra.setComposicaoProduto(cp);
            itensCompra.setValor(cp.getPreco());
            setTeste(cp.toString());
        }
    }
    
    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }
    
    

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<ComposicaoProduto> getComposicaoProduto() {
        return composicaoProduto;
    }

    public void setComposicaoProduto(List<ComposicaoProduto> composicaoProduto) {
        this.composicaoProduto = composicaoProduto;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    public ContasPagar getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(ContasPagar contasPagar) {
        this.contasPagar = contasPagar;
    }

    public Integer getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Integer numParcela) {
        this.numParcela = numParcela;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public ConverterGenerico getConverterComposicaoProduto() {
        if (converterComposicaoProduto == null) {
            converterComposicaoProduto = new ConverterGenerico(produtoFacade);
        }
        return converterComposicaoProduto;
    }

    public void setConverterComposicaoProduto(ConverterGenerico converterComposicaoProduto) {
        this.converterComposicaoProduto = converterComposicaoProduto;
    }

    public ConverterGenerico getConverterProduto() {
        if (converterProduto == null) {
            converterProduto = new ConverterGenerico(produtoFacade);
        }
        return converterProduto;
    }

    public void setConverterProduto(ConverterGenerico converterProduto) {
        this.converterProduto = converterProduto;
    }

    public void addItemComposicaoProduto() {
//        if (itensCompra.getQuantidade() > itensCompra.getComposicaoProduto().getEstoque()) {
//            FacesContext.getCurrentInstance().addMessage(
//                    null, new FacesMessage(
//                            FacesMessage.SEVERITY_ERROR,
//                            "A quantidade inicada é maior que o estoque atual: ",
//                            "" + itensCompra.getComposicaoProduto().getEstoque()));
//        } else {

            Double estoque = itensCompra.getComposicaoProduto().getEstoque();
            ItensCompra itemTemp = null;
            for (ItensCompra it : compra.getItensCompra()) {
                if (it.getComposicaoProduto().equals(itensCompra.getComposicaoProduto())) {
                    estoque = estoque + it.getQuantidade();
                    itemTemp = it;
                }
            }
            if (itemTemp != null) {
                itemTemp.setQuantidade(itemTemp.getQuantidade() + itensCompra.getQuantidade());
            } else {
                itensCompra.setCompra(compra);
                compra.getItensCompra().add(itensCompra);
            }
            itensCompra = new ItensCompra();
            setTeste("old");
        }
//    }

    public void removerItemCompra(ItensCompra it) {
        compra.getItensCompra().remove(it);
    }

    public MoneyConverter getMoneyConverter() {
        if (moneyConverter == null) {
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

    public ItensCompra getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(ItensCompra itensCompra) {
        this.itensCompra = itensCompra;
    }

    public List<Produto> listaFiltrandoComposicaoProduto(String parte) {
        return produtoFacade.listaFiltrando(parte, "nome");
    }

    public List<PessoaJuridica> listaFiltrandoPessoaJuridica(String parte) {
        return pessoaJuridicaFacade.listaFiltrando(parte, "nome");
    }


    public ConverterGenerico getConverterPessoaJuridica() {
        if (converterPessoaJuridica == null) {
            converterPessoaJuridica = new ConverterGenerico(pessoaJuridicaFacade);
        }
        return converterPessoaJuridica;
    }

    public void setConverterPessoaJuridica(ConverterGenerico converterPessoaJuridica) {
        this.converterPessoaJuridica = converterPessoaJuridica;
    }

    public void novo() {
        compra = new Compra();
        itensCompra = new ItensCompra();
    }

    public void excluir(Compra e) {
        compraFacade.excluir(e);
    }

    public void editar(Compra e) {
        this.compra = e;
    }

    public void salvar() {
        compraFacade.salvar(compra);
    }

    public List<Compra> listaTodos() {
        return compraFacade.listaTodos();
    }

    public List<Produto> listaTodosProdutos() {
        return produtoFacade.listaTodos();
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }


    public void geraParcela() {
        compra.setContasPagars(new ArrayList<ContasPagar>());
        Double valor = compra.getValorTotal() / numParcela;
        Date dataVen = compra.getDataCompra();
        for (Integer i = 1; i <= numParcela; i++) {

            if (compra.getFormaPag().equals("Cartão Avista") || compra.getFormaPag().equals("Cartão Parcelado") || compra.getFormaPag().equals("Cheque a Vista") || compra.getFormaPag().equals("Dinheiro Avista")) {
                ContasPagar cr = new ContasPagar();
                cr.setDataLancamento(compra.getDataCompra());
                cr.setParcela(i.toString() + "/" + numParcela.toString());
                cr.setValor(valor);
                cr.setPessoaJuridica(compra.getPessoaJuridica());
                cr.setDataVencimento(dataVen);
                cr.setCompra(compra);
                cr.setFormapag(compra.getFormaPag());

                BaixaContasPagar b = new BaixaContasPagar();
                b.setContasPagar(cr);
                b.setDataBaixa(compra.getDataCompra());
                b.setValor(valor);
                cr.setBaixaContasPagars(new ArrayList<BaixaContasPagar>());
                cr.getBaixaContasPagars().add(b);
                if (compra.getPessoaJuridica() != null) {
                    cr.setPessoaJuridica(compra.getPessoaJuridica());
                }
                compra.getContasPagars().add(cr);
                //Soma 1 mês no vencimento
                Calendar cal = Calendar.getInstance();
                cal.setTime(dataVen);
                cal.add(Calendar.MONTH, 1);
                dataVen = cal.getTime();

            } else {
                ContasPagar cr = new ContasPagar();
                cr.setDataLancamento(compra.getDataCompra());
                cr.setParcela(i.toString() + "/" + numParcela.toString());
                cr.setValor(valor);
                cr.setPessoaJuridica(compra.getPessoaJuridica());
                cr.setDataVencimento(dataVen);
                cr.setCompra(compra);
                cr.setFormapag(compra.getFormaPag());

                if (compra.getPessoaJuridica() != null) {
                    cr.setPessoaJuridica(compra.getPessoaJuridica());
                }
                compra.getContasPagars().add(cr);
                //Soma 1 mês no vencimento
                Calendar cal = Calendar.getInstance();
                cal.setTime(dataVen);
                cal.add(Calendar.MONTH, 1);
                dataVen = cal.getTime();
            }
        }
    }
}
