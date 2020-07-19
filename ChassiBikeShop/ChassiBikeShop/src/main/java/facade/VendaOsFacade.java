package facade;

import entidade.ComposicaoProduto;
import entidade.VendaOs;
import entidade.ItensVendaOs;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class VendaOsFacade extends AbstractFacade<VendaOs>{

    @Inject
    private EntityManager em;

    public VendaOsFacade() {
        super(VendaOs.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

    @Override
    public void salvar(VendaOs co) {        
        super.salvar(co); 
        diminuiEstoque(co);
    }

    private void diminuiEstoque(VendaOs co) {
        for(ItensVendaOs it : co.getItensVendaOs()){
            ComposicaoProduto p = it.getComposicaoProduto();
            p.setEstoque(p.getEstoque() - it.getQuantidade());
            em.merge(p);
        }
    }
    
   
       @Override
    public void excluir(VendaOs ve) {
        for(ItensVendaOs it : ve.getItensVendaOs()){
           ComposicaoProduto p = it.getComposicaoProduto();
            p.setEstoque(p.getEstoque() + it.getQuantidade());
            em.merge(p);
        }
        super.excluir(ve);
    }
    
    }
    
    
    
    

