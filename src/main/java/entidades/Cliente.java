package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"cpfcpnj"})})
public class Cliente implements Serializable, ClassePai {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cpfcpnj;
    private Boolean possuiConta = false;

    // Gets e Sets
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfcpnj() {
        return cpfcpnj;
    }

    public void setCpfcpnj(String cpfcpnj) {
        this.cpfcpnj = cpfcpnj;
    }

    public Boolean getPossuiConta() {
        return possuiConta;
    }

    public void setPossuiConta(Boolean possuiConta) {
        this.possuiConta = possuiConta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(nome, cliente.nome) && Objects.equals(cpfcpnj, cliente.cpfcpnj) && Objects.equals(possuiConta, cliente.possuiConta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpfcpnj, possuiConta);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                '}';
    }
}
