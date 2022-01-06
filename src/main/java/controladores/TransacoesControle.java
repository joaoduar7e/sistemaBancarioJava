/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.util.JsfUtil;
import converter.ConverterGenerico;
import converter.ConverterGenerico;
import entidades.Conta;
import entidades.Transacoes;
import facade.ContaFacade;
import facade.TransacoesFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewAccessScoped
public class TransacoesControle implements Serializable {

    private Transacoes transacoes;
    @Inject
    transient private TransacoesFacade transacoesFacade;

    private Conta contaDestino;
    private Conta conta;

    @Inject
    transient private ContaFacade contaFacade;
    private ConverterGenerico contaConverter;

    public ConverterGenerico getContaConverter() {
        if (contaConverter == null) {
            contaConverter = new ConverterGenerico(contaFacade);
        }
        return contaConverter;
    }

    public void setContaConverter(ConverterGenerico contaConverter) {
        this.contaConverter = contaConverter;
    }

    public List<Conta> getListacontasFiltrando(String parte) {
        return contaFacade.listaFiltrando(parte, "conta");
    }

    public List<Conta> getListaContas() {
        return contaFacade.listaTodos();
    }

    public List<Transacoes> getListaTrans() {
        return transacoesFacade.listaTodos();
    }


    public void novo() {
        transacoes = new Transacoes();
    }

    public List<Conta> getContasList() {
        List<Conta> conta = new ArrayList<>();
        if (transacoes.getConta() != null) {
            conta.add(transacoes.getConta());
        }
        return conta;
    }

    private boolean verificaContasIguais() {
        try {
            if (transacoes.getContaD().equals(transacoes.getConta())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void salvar() throws Exception {
        if (verificaContasIguais()) {
            JsfUtil.adicionarMenssagemErro("É necessário selecionar uma conta de origem diferente da conta de destino!!!");
            return;
        } else {
            for (Conta c : getContasList()) {
                if (transacoes.getTipo().equals("S")) {
                    if (c.getValorConta() < transacoes.getQtdTotal()) {
                        FacesContext.getCurrentInstance().addMessage(
                                null, new FacesMessage(
                                        FacesMessage.SEVERITY_ERROR,
                                        "Valor em conta insuficiente para a operação!",
                                        ""));
                        return;
                    } else {
                        c.setValorConta(c.getValorConta() - transacoes.getQtdTotal());
                        transacoes.getContaD().setValorConta(transacoes.getContaD().getValorConta() + transacoes.getQtdTotal());
                    }
                } else if (transacoes.getTipo().equals("D")) {
                    c.setValorConta(c.getValorConta() + transacoes.getQtdTotal());
                }

                if (transacoes.getTipo().equals("D") && transacoes.getQtdTotal() >= 2000) {
                    FacesContext.getCurrentInstance().addMessage(
                            null, new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR,
                                    "O valor de depósito não pode ser superior a R$2000!",
                                    ""));
                    return;
                } else if (c != null) {
                    contaFacade.contaMerge(c);
                    transacoesFacade.salvar(transacoes);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("list.xhtml");
                }
            }
        }
    }

    public void editar(Transacoes t) {
        transacoes = t;
    }

    public void excluir(Transacoes t) {
        transacoesFacade.remover(t);
    }

    public Transacoes getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(Transacoes transacoes) {
        this.transacoes = transacoes;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Conta contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
