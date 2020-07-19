/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.Cidade;
import entidade.PessoaJuridica;
import facade.CidadeFacade;
import facade.PessoaJuridicaFacade;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author carol
 */
@Named
@SessionScoped
public class PessoaJuridicaControle implements Serializable {

    @Inject
    private PessoaJuridicaFacade pessoaJuridicaFacade;
    private PessoaJuridica pessoaJuridica;
    private String tipoPessoa;
    @Inject
    private CidadeFacade cidadeFacade;
    private ConverterGenerico converterCidade;


    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
    
    

    public void salvar() {
        pessoaJuridicaFacade.salvar(pessoaJuridica);
    }

    public List<Cidade> listaFiltrando(String parte) {
        return cidadeFacade.listaFiltrando(parte, "nome");
    }

    public ConverterGenerico getConverterCidade() {
        if (converterCidade == null) {
            converterCidade = new ConverterGenerico(cidadeFacade);
        }
        return converterCidade;
    }

    public void setConverterCidade(ConverterGenerico converterCidade) {
        this.converterCidade = converterCidade;
    }

    public void novo() {
        pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setCadastro("PessoaJuridica");
    }

    public void excluir(PessoaJuridica e) {
        pessoaJuridicaFacade.excluir(e);
    }

    public void editar(PessoaJuridica e) {
        this.pessoaJuridica = e;
    }

    public List<PessoaJuridica> listaTodos() {
        return pessoaJuridicaFacade.listaTodos();
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

}
