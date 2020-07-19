package facade;

import entidade.AjusteEstoque;
import entidade.ItensAjusteEstoque;
import entidade.Produto;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class AjusteEstoqueFacade extends AbstractFacade<AjusteEstoque>{

    @Inject
    private EntityManager em;

    public AjusteEstoqueFacade() {
        super(AjusteEstoque.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

    @Override
    public void salvar(AjusteEstoque co) {        
        super.salvar(co); 
//        somaEstoque(co);
    }

//    private void somaEstoque(AjusteEstoque co) {
//        for(ItensAjusteEstoque it : co.getItensAjusteEstoques()){
//            Produto p = it.getProduto();
//            p.setEstoque(p.getEstoque() - it.getQuantidade());
//            em.merge(p);
//        }
//    }
//    
//   
//       @Override
//    public void excluir(AjusteEstoque ve) {
//        for(ItensAjusteEstoque it : ve.getItensAjusteEstoques()){
//            Produto p = it.getProduto();
//            p.setEstoque(p.getEstoque() + it.getQuantidade());
//            em.merge(p);
//        }
//        super.excluir(ve);
//    }
    
    }
    
    
    
    

