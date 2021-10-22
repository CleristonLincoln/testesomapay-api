package br.com.somapay.domain.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "financeiro", name = "transferencia")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = TransferenciaOutroEmpresa.class, name = "toe"),
        @JsonSubTypes.Type(value = TransferenciaEmpresaFuncionario.class, name = "tef"),
        @JsonSubTypes.Type(value = TransferenciaFuncionarioOutro.class, name = "teo")})
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private LocalDateTime dataCriacao;

    @OneToOne
    @JoinColumn(name = "origem")
    private Conta contaOrigem;

    @OneToOne
    @JoinColumn(name = "destino")
    private  Conta contaDestivo;

    @Positive
    private BigDecimal valor;


    public Transferencia(String descricao, LocalDateTime dataCriacao, Conta contaOrigem, Conta contaDestivo,
                         BigDecimal valor) {
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.contaOrigem = contaOrigem;
        this.contaDestivo = contaDestivo;
        this.valor = valor;
    }

    public Transferencia() {

    }
}
