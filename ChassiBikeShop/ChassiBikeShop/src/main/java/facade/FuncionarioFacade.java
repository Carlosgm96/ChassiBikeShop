package facade;

import entidade.Funcionario;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class FuncionarioFacade extends AbstractFacade<Funcionario>{

    @Inject
    private EntityManager em;

    public FuncionarioFacade() {
        super(Funcionario.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
