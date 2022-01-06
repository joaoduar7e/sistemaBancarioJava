package facade;

import entidades.Cliente;
import entidades.Conta;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class ContaFacade extends AbstractFacade<Conta> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void contaMerge(Conta c) {
        em.merge(c);
    }


    public ContaFacade() {
        super(Conta.class);
    }
}
