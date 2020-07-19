package facade;

import entidade.ComposicaoProduto;
import entidade.Compra;
import entidade.ItensCompra;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class CompraFacade extends AbstractFacade<Compra>{

    @Inject
    private EntityManager em;

    public CompraFacade() {
        super(Compra.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

    @Override
    public void salvar(Compra co) {        
        super.salvar(co); 
        somaEstoque(co);
    }

    private void somaEstoque(Compra co) {
        for(ItensCompra it : co.getItensCompra()){
            ComposicaoProduto p = it.getComposicaoProduto();
            p.setEstoque(p.getEstoque() + it.getQuantidade());
            em.merge(p);
        }
    }
    
   
       @Override
    public void excluir(Compra ve) {
        for(ItensCompra it : ve.getItensCompra()){
           ComposicaoProduto p = it.getComposicaoProduto();
            p.setEstoque(p.getEstoque() - it.getQuantidade());
            em.merge(p);
        }
        super.excluir(ve);
    }
    
    }
    
    
    
    

