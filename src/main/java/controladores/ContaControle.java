package controladores;

import controladores.util.JsfUtil;
import converter.ConverterGenerico;
import entidades.Cliente;
import entidades.Conta;
import facade.ClienteFacade;
import facade.ContaFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import org.primefaces.util.Jsf23Helper;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class ContaControle implements Serializable {
    private Conta conta = new Conta();

    @Inject
    transient private ContaFacade contaFacade;

    @Inject
    transient private ClienteFacade clienteFacade;

    private ConverterGenerico clienteConverter;

    public ConverterGenerico getClienteConverter() {
        if (clienteConverter == null) {
            clienteConverter = new ConverterGenerico(clienteFacade);
        }
        return clienteConverter;
    }

    public void setClienteConverter(ConverterGenerico clienteConverter) {
        this.clienteConverter = clienteConverter;
    }

    public List<Cliente> getListaClientesFiltrando(String parte) {
        return clienteFacade.listaFiltrando(parte, "nome");
    }

    public List<Conta> getListaConta() {
        return contaFacade.listaTodos();
    }

    public void novo() {
        conta = new Conta();
    }

    public void editar(Conta c) {
        conta = c;
    }

    public void excluir(Conta c) {
        try {
            c.getCliente().setPossuiConta(false);
            contaFacade.remover(c);
        } catch (Exception e) {
            JsfUtil.adicionarMenssagemErro("Não é possível excluir um dado que já" +
                    " tenha sido utilizado em alguma movimentaçâo!");
        }
    }

    public void salvar() throws IOException {
        try {
            if (conta.getConta().length() != 7) {
                JsfUtil.adicionarMenssagemErro("O número da conta deve conter '6' caracteres!");
                return;
            } else if (conta.getCliente().getPossuiConta()) {
                JsfUtil.adicionarMenssagemErro("Esse cliente já possui uma conta associada a ele!");
            } else {
                conta.getCliente().setPossuiConta(true);
                contaFacade.salvar(conta);
                conta = new Conta();
                FacesContext.getCurrentInstance().getExternalContext().redirect("list.xhtml");
            }
        } catch (Exception e) {
            JsfUtil.adicionarMenssagemErro("Já existe uma conta com esse dado, digite um novo valor!");
        }

    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}

