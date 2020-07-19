/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.Cidade;
import entidade.Funcionario;
import facade.CidadeFacade;
import facade.FuncionarioFacade;
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
public class FuncionarioControle implements Serializable {

    @Inject
    private FuncionarioFacade funcionarioFacade;
    @Inject
    private CidadeFacade cidadeFacade;
    private Funcionario funcionario;
    private ConverterGenerico converterCidade;
  
    
    public void salvar(){
        funcionarioFacade.salvar(funcionario);
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
        funcionario = new Funcionario();
        funcionario.setCadastro("Funcionario");
    }
    
    public void excluir(Funcionario e){
        funcionarioFacade.excluir(e);
    }
    
    public void editar(Funcionario e){
        this.funcionario = e;
    }

    public List<Funcionario> listaTodos() {
        return funcionarioFacade.listaTodos();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

}
