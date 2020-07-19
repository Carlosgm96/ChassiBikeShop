/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.Pessoa;
import entidade.PessoaFisica;
import entidade.Funcionario;
import entidade.PessoaJuridica;
import facade.PessoaFacade;
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
public class PessoaControle implements Serializable {

    @Inject
    private PessoaFacade pessoaFacade;
    private Pessoa pessoa;
    @Inject
    private PessoaFisicaControle pessoaFisicaControle;
    @Inject
    private PessoaJuridicaControle pessoaJuridicaControle;
    @Inject
    private FuncionarioControle funcionarioControle;

    public PessoaFisicaControle getPessoaFisicaControle() {
        return pessoaFisicaControle;
    }

    public void setPessoaFisicaControle(PessoaFisicaControle pessoaFisicaControle) {
        this.pessoaFisicaControle = pessoaFisicaControle;
    }

    public PessoaJuridicaControle getPessoaJuridicaControle() {
        return pessoaJuridicaControle;
    }

    public void setPessoaJuridicaControle(PessoaJuridicaControle pessoaJuridicaControle) {
        this.pessoaJuridicaControle = pessoaJuridicaControle;
    }
    

    public void excluir(Pessoa e){
        if(e instanceof PessoaFisica){
            pessoaFisicaControle.excluir((PessoaFisica)e);
        }else{
            pessoaJuridicaControle.excluir((PessoaJuridica)e);
        }
    }
    
    public String editar(Pessoa p){
        if(p instanceof PessoaFisica){
            pessoaFisicaControle.editar((PessoaFisica)p);
            return "editapessoafisica";
        }else if(p instanceof Funcionario){
            funcionarioControle.editar((Funcionario)p);
            return "editafuncionario";
        }else{
            pessoaJuridicaControle.editar((PessoaJuridica)p);
            return "editapessoajuridica";
        }
    }   

    public List<Pessoa> listaTodos() {
        return pessoaFacade.listaTodos();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
