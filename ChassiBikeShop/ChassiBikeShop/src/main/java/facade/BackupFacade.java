package facade;

import entidade.IncrementoBKP;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class BackupFacade extends AbstractFacade<IncrementoBKP>{

    @Inject
    private EntityManager em;

    public BackupFacade() {
        super(IncrementoBKP.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

    @Override
    public void salvar(IncrementoBKP co) {        
        super.salvar(co); 
//        somaEstoque(co);
    }
}