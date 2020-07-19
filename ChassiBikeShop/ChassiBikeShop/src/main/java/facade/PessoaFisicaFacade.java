package facade;

import entidade.Pessoa;
import entidade.PessoaFisica;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class PessoaFisicaFacade extends AbstractFacade<PessoaFisica>{

    @Inject
    private EntityManager em;

    public PessoaFisicaFacade() {
        super(PessoaFisica.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
