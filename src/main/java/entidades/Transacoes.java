package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
public class Transacoes implements Serializable, ClassePai {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataTransacao;

    @ManyToOne
    private Conta conta;

    @ManyToOne
    private Conta contaD;

    private Double qtdTotal;

    private String tipo = "D";

    public Transacoes() { //metodo construtors
        dataTransacao = new Date(); //pega a data do servidor
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Double getQtdTotal() {
        return qtdTotal;
    }

    public void setQtdTotal(Double qtdTotal) {
        this.qtdTotal = qtdTotal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Conta getContaD() {
        return contaD;
    }

    public void setContaD(Conta contaD) {
        this.contaD = contaD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacoes that = (Transacoes) o;
        return Objects.equals(id, that.id) && Objects.equals(dataTransacao, that.dataTransacao) && Objects.equals(conta, that.conta) && Objects.equals(contaD, that.contaD) && Objects.equals(qtdTotal, that.qtdTotal) && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataTransacao, conta, contaD, qtdTotal, tipo);
    }

    @Override
    public String toString() {
        return "Transacoes" +
                "[ id=" + id + " ]";
    }

}
