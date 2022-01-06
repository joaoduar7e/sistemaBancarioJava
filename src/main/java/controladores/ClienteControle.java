package controladores;

import controladores.util.JsfUtil;
import entidades.Cliente;
import facade.ClienteFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@ViewAccessScoped
public class ClienteControle implements Serializable {
    private Cliente cliente = new Cliente();

    @Inject
    transient private ClienteFacade clienteFacade;

    public List<Cliente> getListaCliente() {
        return clienteFacade.listaTodos();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void novo() {
        cliente = new Cliente();
    }

    public void editar(Cliente cli) {
        cliente = cli;
    }

    public void excluir(Cliente cli) {
        try {
            clienteFacade.remover(cli);
        } catch (Exception e) {
            JsfUtil.adicionarMenssagemErro("Não é possível excluir um dado que já" +
                    " tenha sido utilizado em alguma movimentaçâo!");
        }
    }

    public void salvar() throws IOException {
        try {
            if (cliente.getCpfcpnj().length() != 14) {
                JsfUtil.adicionarMenssagemErro("O CPF deve conter 11 caracteres!");
            } else {
                clienteFacade.salvar(cliente);
                cliente = new Cliente();
                FacesContext.getCurrentInstance().getExternalContext().redirect("list.xhtml");
            }
        } catch (Exception e) {
            JsfUtil.adicionarMenssagemErro("CPF já cadastrado, digite um novo valor!");
        }

    }

}
