package br.com.somapay.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "financeiro", name = "transferencia_funcionario_outro")
@PrimaryKeyJoinColumn(name = "transferencia_id")
public class TransferenciaFuncionarioOutro extends Transferencia {

    @ManyToOne
    @JoinColumn(nullable = false, name = "funcionario_id")
    private Funcionario funcionario;

    public TransferenciaFuncionarioOutro(Funcionario funcionario, String descricao, LocalDateTime dataCriacao,
                                     Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
        super(descricao, dataCriacao, contaOrigem, contaDestino, valor);
        this.funcionario = funcionario;
    }
}
