package facade;

import entidade.Tamanho;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class TamanhoFacade extends AbstractFacade<Tamanho>{

    @Inject
    private EntityManager em;

    public TamanhoFacade() {
        super(Tamanho.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
