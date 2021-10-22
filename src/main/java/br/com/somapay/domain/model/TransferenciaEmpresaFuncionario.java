package br.com.somapay.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity

@Table(schema = "financeiro", name = "transferencia_empresa_funcionario")
@PrimaryKeyJoinColumn(name = "transferencia_id")
public class TransferenciaEmpresaFuncionario extends Transferencia {

    @ManyToOne
    @JoinColumn(nullable = false, name = "empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(nullable = false, name = "funcionario_id")
    private Funcionario funcionario;

    public TransferenciaEmpresaFuncionario() {
        super();
    }

    public TransferenciaEmpresaFuncionario(Empresa empresa, Funcionario funcionario, String descricao,
                                           LocalDateTime dataCriacao, Conta contaOrigem, Conta contaDestino,
                                           BigDecimal valor) {
        super(descricao, dataCriacao, contaOrigem, contaDestino, valor);
        this.empresa = empresa;
        this.funcionario = funcionario;
    }

}
