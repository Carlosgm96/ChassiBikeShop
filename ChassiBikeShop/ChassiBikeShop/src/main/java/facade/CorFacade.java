package facade;

import entidade.Cor;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class CorFacade extends AbstractFacade<Cor>{

    @Inject
    private EntityManager em;

    public CorFacade() {
        super(Cor.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
