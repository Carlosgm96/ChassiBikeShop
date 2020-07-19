package facade;

import entidade.Pessoa;
import entidade.PessoaJuridica;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class PessoaJuridicaFacade extends AbstractFacade<PessoaJuridica>{

    @Inject
    private EntityManager em;

    public PessoaJuridicaFacade() {
        super(PessoaJuridica.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
