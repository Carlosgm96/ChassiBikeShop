/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.Tamanho;
import facade.TamanhoFacade;
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
public class TamanhoControle implements Serializable {

    @Inject
    private TamanhoFacade tamanhoFacade;
    private Tamanho tamanho;

    public void novo(){
        tamanho = new Tamanho();
    }
    
    public void excluir(Tamanho m){
        tamanhoFacade.excluir(m);
    }
    
    public void editar(Tamanho m){
        this.tamanho = m;
    }
    
    public void salvar() {
        tamanhoFacade.salvar(tamanho);
    }

    public List<Tamanho> listaTodos() {
        return tamanhoFacade.listaTodos();
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

}
