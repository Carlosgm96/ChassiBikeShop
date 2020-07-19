/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.Cor;
import entidade.GrupoProduto;
import entidade.ComposicaoProduto;
import entidade.Produto;
import entidade.Tamanho;
import facade.CorFacade;
import facade.GrupoProdutoFacade;
import facade.ProdutoFacade;
import facade.TamanhoFacade;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author carol
 */
@Named
@SessionScoped
public class ProdutoControle implements Serializable {

    @Inject
    private ProdutoFacade produtoFacade;
    @Inject
    private GrupoProdutoFacade grupoProdutoFacade;
    @Inject
    private CorFacade corFacade;
    @Inject
    private TamanhoFacade tamanhoFacade;
    private Produto produto;
    private Cor cor;
    private Tamanho tamanho;
    private MoneyConverter moneyConverter;
    private ConverterGenerico grupoProduroConverter;
    private ConverterGenerico corConverter;
    private ConverterGenerico tamanhoConverter;
    private ComposicaoProduto composicaoProduto = new ComposicaoProduto();

    public ConverterGenerico getTamanoConverter() {
        if (tamanhoConverter == null) {
            tamanhoConverter = new ConverterGenerico(tamanhoFacade);
        }
        return tamanhoConverter;
    }

    public void setTamanhoConverter(ConverterGenerico tamanhoConverter) {
        this.tamanhoConverter = tamanhoConverter;
    }

    public ConverterGenerico getCorConverter() {
        if (corConverter == null) {
            corConverter = new ConverterGenerico(corFacade);
        }
        return corConverter;
    }

    public void setCorConverter(ConverterGenerico corConverter) {
        this.corConverter = corConverter;
    }

    
    public ConverterGenerico getGrupoProdutoConverter() {
        if (grupoProduroConverter == null) {
            grupoProduroConverter = new ConverterGenerico(grupoProdutoFacade);
        }
        return grupoProduroConverter;
    }

    public void setGrupoProdutoConverter(ConverterGenerico grupoProduroConverter) {
        this.grupoProduroConverter = grupoProduroConverter;
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

    public ComposicaoProduto getComposicaoProduto() {
        return composicaoProduto;
    }

    public void setComposicaoProduto(ComposicaoProduto composicaoProduto) {
        this.composicaoProduto = composicaoProduto;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public void novo() {
        produto = new Produto();
        composicaoProduto = new ComposicaoProduto();
    }

    public void excluir(Produto e) {
        produtoFacade.excluir(e);
    }

    public void editar(Produto e) {
        this.produto = e;
    }

    public void salvar() {
        produtoFacade.salvar(produto);
    }

    public List<Produto> listaTodos() {
        return produtoFacade.listaTodos();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

// Metodo de adicionar    
    public void addEstoque() {
//        for (ComposicaoProduto it : produto.getComposicaoProduto()) {
//            if (it.getCor().equals(composicaoProduto.getCor()) || it.getTamanho().equals(composicaoProduto.getTamanho())) {
//                 FacesContext.getCurrentInstance().addMessage(
//                    null, new FacesMessage(
//                            FacesMessage.SEVERITY_ERROR,
//                            "Produto já existente na base de dados",
//                            "se deseja alterar o estoque faça pelo movimento de ajuste de estoque"));
//            }else{
//        
        produto.getComposicaoProduto().add(composicaoProduto);
        composicaoProduto.setProduto(produto);
        composicaoProduto = new ComposicaoProduto();

//            }
//        }
    }

    public void removerItemEstoque(ComposicaoProduto it) {
//        diminuiEstoque();
        produto.getComposicaoProduto().remove(it);
    }

    public List<GrupoProduto> listaFiltrandoGrupoProduto(String parte) {
        return grupoProdutoFacade.listaFiltrando(parte, "nome");
    }

    public List<Cor> listaFiltrandoCor(String parte) {
        return corFacade.listaFiltrando(parte, "nome");
    }

    public List<Tamanho> listaFiltrandoTamanho(String parte) {
        return tamanhoFacade.listaFiltrando(parte, "nome");
    }

}
