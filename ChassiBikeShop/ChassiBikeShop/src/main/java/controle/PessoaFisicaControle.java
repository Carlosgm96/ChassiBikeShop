/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.Cidade;
import entidade.PessoaFisica;
import facade.CidadeFacade;
import facade.PessoaFisicaFacade;
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
public class PessoaFisicaControle implements Serializable {

    @Inject
    private PessoaFisicaFacade pessoaFisicaFacade;
    @Inject
    private CidadeFacade cidadeFacade;
    private PessoaFisica pessoaFisica;
    private ConverterGenerico converterCidade;
  
    
    public void salvar(){
        pessoaFisicaFacade.salvar(pessoaFisica);
    }
    
    public List<Cidade> listaFiltrando(String parte){
        return cidadeFacade.listaFiltrando(parte, "nome");
    }

    public ConverterGenerico getConverterCidade() {
        if(converterCidade == null){
            converterCidade = new ConverterGenerico(cidadeFacade);
        }
        return converterCidade;
    }

    public void setConverterCidade(ConverterGenerico converterCidade) {
        this.converterCidade = converterCidade;
    }
    
    
    public void novo(){
        pessoaFisica = new PessoaFisica();
        pessoaFisica.setCadastro("PessoaFisica");
    }
    
    public void excluir(PessoaFisica e){
        pessoaFisicaFacade.excluir(e);
    }
    
    public void editar(PessoaFisica e){
        this.pessoaFisica = e;
    }

    public List<PessoaFisica> listaTodos() {
        return pessoaFisicaFacade.listaTodos();
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

}
