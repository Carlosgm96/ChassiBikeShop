package facade;

import entidade.Produto;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class ProdutoFacade extends AbstractFacade<Produto>{

    @Inject
    private EntityManager em;

    public ProdutoFacade() {
        super(Produto.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @Override
    public void salvar(Produto pr) {        
        super.salvar(pr); 
//        baixaEstoque(pr);
    }
    
}
