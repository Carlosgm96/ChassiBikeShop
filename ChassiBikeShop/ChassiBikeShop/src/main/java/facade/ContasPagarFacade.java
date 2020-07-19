package facade;

import entidade.ContasPagar;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class ContasPagarFacade extends AbstractFacade<ContasPagar>{

    @Inject
    private EntityManager em;

    public ContasPagarFacade() {
        super(ContasPagar.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
