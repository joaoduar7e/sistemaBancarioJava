package facade;

import entidades.Conta;
import entidades.Transacoes;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class TransacoesFacade extends AbstractFacade<Transacoes> {
    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager(){return em;}

    public TransacoesFacade(){ super(Transacoes.class);}
}
