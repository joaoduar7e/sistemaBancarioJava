package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"conta"})})
public class Conta implements Serializable, ClassePai {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Cliente cliente;
    @Column
    private String conta;
    @Column
    private Double valorConta = 0d;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Double getValorConta() {
        return valorConta;
    }

    public void setValorConta(Double valorConta) {
        this.valorConta = valorConta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta1 = (Conta) o;
        return Objects.equals(id, conta1.id) && Objects.equals(cliente, conta1.cliente) && Objects.equals(conta, conta1.conta) && Objects.equals(valorConta, conta1.valorConta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, conta, valorConta);
    }

    @Override
    public String toString() {
        return "Conta" +
                "[ id=" + id + " ]";
    }

}
