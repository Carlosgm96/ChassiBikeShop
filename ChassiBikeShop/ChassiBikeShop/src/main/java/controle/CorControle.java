/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.Cor;
import facade.CorFacade;
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
public class CorControle implements Serializable {

    @Inject
    private CorFacade corFacade;
    private Cor cor;

    public void novo(){
        cor = new Cor();
    }
    
    public void excluir(Cor m){
        corFacade.excluir(m);
    }
    
    public void editar(Cor m){
        this.cor = m;
    }
    
    public void salvar() {
        corFacade.salvar(cor);
    }

    public List<Cor> listaTodos() {
        return corFacade.listaTodos();
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

}
