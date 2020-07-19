package facade;

import entidade.ContasReceber;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class ContasReceberFacade extends AbstractFacade<ContasReceber>{

    @Inject
    private EntityManager em;

    public ContasReceberFacade() {
        super(ContasReceber.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
