package br.com.somapay.domain.model.to;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
public class FuncionariosResumidoTO {
    private BigInteger id;
    private String nome;
    private String cpf;
    private BigDecimal valor;
    private BigInteger empresaId;

    public FuncionariosResumidoTO(Object[] obj) {
        this.id = (BigInteger) obj[0];
        this.nome = (String) obj[1];
        this.cpf = (String) obj[2];
        this.valor = (BigDecimal) obj[3];
        this.empresaId = (BigInteger) obj[4];
    }
}
