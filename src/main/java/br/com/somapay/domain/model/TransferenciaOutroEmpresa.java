package br.com.somapay.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(schema = "financeiro", name = "transferencia_outro_empresa")
@PrimaryKeyJoinColumn(name = "transferencia_id")
public class TransferenciaOutroEmpresa extends Transferencia {


    @ManyToOne
    @JoinColumn(nullable = false, name = "empresa_id")
    private Empresa empresa;

    public TransferenciaOutroEmpresa() {
        super();
    }

    public TransferenciaOutroEmpresa(Empresa empresa, String descricao, LocalDateTime dataCriacao,
                                     Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
        super(descricao, dataCriacao, contaOrigem, contaDestino, valor);
        this.empresa = empresa;
    }
}
