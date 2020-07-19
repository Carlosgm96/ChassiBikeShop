/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.BaixaContasReceber;
import entidade.Pessoa;
import entidade.ComposicaoProduto;
import entidade.ContasReceber;
import entidade.ItensVendaOs;
import entidade.VendaOs;
import entidade.Produto;
import facade.PessoaFacade;
import facade.VendaOsFacade;
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
public class VendaOsControle implements Serializable {

    @Inject
    private VendaOsFacade vendaOsFacade;
    private VendaOs vendaOs;
    private ItensVendaOs itensVendaOs;
    @Inject
    private PessoaFacade pessoaFacade;
    @Inject
    private ProdutoFacade produtoFacade;
    private ConverterGenerico converterPessoa;
    private ConverterGenerico converterComposicaoProduto;
    private ConverterGenerico converterProduto;
    private MoneyConverter moneyConverter;
    private Integer numParcela;
    private Date dtVencimento;
    private ContasReceber contasReceber;
    private Pessoa pessoa;
    private Produto produto;
    private String teste = "old";
    private List<ComposicaoProduto> composicaoProduto;
    
        public String getSituacao(){
            return "Baixado";
    }

    public void setarComposicao(ComposicaoProduto cp) {
        if (itensVendaOs.getQuantidade().equals(0d)) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Obrigatório adicionar a quantidade de produto",
                            ""));
        } else {

            itensVendaOs.setComposicaoProduto(cp);
            itensVendaOs.setValor(cp.getPreco());
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
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
//        if (itensVendaOs.getQuantidade() > itensVendaOs.getComposicaoProduto().getEstoque()) {
//            FacesContext.getCurrentInstance().addMessage(
//                    null, new FacesMessage(
//                            FacesMessage.SEVERITY_ERROR,
//                            "A quantidade inicada é maior que o estoque atual: ",
//                            "" + itensVendaOs.getComposicaoProduto().getEstoque()));
//        } else {

            Double estoque = itensVendaOs.getComposicaoProduto().getEstoque();
            ItensVendaOs itemTemp = null;
            for (ItensVendaOs it : vendaOs.getItensVendaOs()) {
                if (it.getComposicaoProduto().equals(itensVendaOs.getComposicaoProduto())) {
                    estoque = estoque + it.getQuantidade();
                    itemTemp = it;
                }
            }
            if (itemTemp != null) {
                itemTemp.setQuantidade(itemTemp.getQuantidade() + itensVendaOs.getQuantidade());
            } else {
                itensVendaOs.setVendaOs(vendaOs);
                vendaOs.getItensVendaOs().add(itensVendaOs);
            }
            itensVendaOs = new ItensVendaOs();
            setTeste("old");
        }
//    }

    public void removerItemVendaOs(ItensVendaOs it) {
        vendaOs.getItensVendaOs().remove(it);
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

    public ItensVendaOs getItensVendaOs() {
        return itensVendaOs;
    }

    public void setItensVendaOs(ItensVendaOs itensVendaOs) {
        this.itensVendaOs = itensVendaOs;
    }

    public List<Produto> listaFiltrandoComposicaoProduto(String parte) {
        return produtoFacade.listaFiltrando(parte, "nome");
    }

    public List<Pessoa> listaFiltrandoPessoa(String parte) {
        return pessoaFacade.listaFiltrando(parte, "nome");
    }


    public ConverterGenerico getConverterPessoa() {
        if (converterPessoa == null) {
            converterPessoa = new ConverterGenerico(pessoaFacade);
        }
        return converterPessoa;
    }

    public void setConverterPessoa(ConverterGenerico converterPessoa) {
        this.converterPessoa = converterPessoa;
    }

    public void novo() {
        vendaOs = new VendaOs();
        itensVendaOs = new ItensVendaOs();
    }

    public void excluir(VendaOs e) {
        vendaOsFacade.excluir(e);
    }

    public void editar(VendaOs e) {
        this.vendaOs = e;
    }

    public void salvar() {
        vendaOsFacade.salvar(vendaOs);
    }

    public List<VendaOs> listaTodos() {
        return vendaOsFacade.listaTodos();
    }

    public List<Produto> listaTodosProdutos() {
        return produtoFacade.listaTodos();
    }

    public VendaOs getVendaOs() {
        return vendaOs;
    }

    public void setVendaOs(VendaOs vendaOs) {
        this.vendaOs = vendaOs;
    }


    public void geraParcela() {
        vendaOs.setContasRecebers(new ArrayList<ContasReceber>());
        Double valor = vendaOs.getValorTotal() / numParcela;
        Date dataVen = vendaOs.getDataVendaOs();
        for (Integer i = 1; i <= numParcela; i++) {

            if (vendaOs.getFormaPag().equals("Cartão Avista") || vendaOs.getFormaPag().equals("Cartão Parcelado") || vendaOs.getFormaPag().equals("Cheque a Vista") || vendaOs.getFormaPag().equals("Dinheiro Avista")) {
                ContasReceber cr = new ContasReceber();
                cr.setDataLancamento(vendaOs.getDataVendaOs());
                cr.setParcela(i.toString() + "/" + numParcela.toString());
                cr.setValor(valor);
                cr.setPessoa(vendaOs.getPessoa());
                cr.setDataVencimento(dataVen);
                cr.setVendaOs(vendaOs);
                cr.setFormapag(vendaOs.getFormaPag());

                BaixaContasReceber b = new BaixaContasReceber();
                b.setContasReceber(cr);
                b.setDataBaixa(vendaOs.getDataVendaOs());
                b.setValor(valor);
                cr.setBaixaContasRecebers(new ArrayList<BaixaContasReceber>());
                cr.getBaixaContasRecebers().add(b);
                if (vendaOs.getPessoa() != null) {
                    cr.setPessoa(vendaOs.getPessoa());
                }
                vendaOs.getContasRecebers().add(cr);
                //Soma 1 mês no vendaOscimento
                Calendar cal = Calendar.getInstance();
                cal.setTime(dataVen);
                cal.add(Calendar.MONTH, 1);
                dataVen = cal.getTime();

            } else {
                ContasReceber cr = new ContasReceber();
                cr.setDataLancamento(vendaOs.getDataVendaOs());
                cr.setParcela(i.toString() + "/" + numParcela.toString());
                cr.setValor(valor);
                cr.setPessoa(vendaOs.getPessoa());
                cr.setDataVencimento(dataVen);
                cr.setVendaOs(vendaOs);
                cr.setFormapag(vendaOs.getFormaPag());

                if (vendaOs.getPessoa() != null) {
                    cr.setPessoa(vendaOs.getPessoa());
                }
                vendaOs.getContasRecebers().add(cr);
                //Soma 1 mês no vendaOscimento
                Calendar cal = Calendar.getInstance();
                cal.setTime(dataVen);
                cal.add(Calendar.MONTH, 1);
                dataVen = cal.getTime();
            }
        }
    }
}
