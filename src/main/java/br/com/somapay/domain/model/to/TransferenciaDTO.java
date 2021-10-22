package br.com.somapay.domain.model.to;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferenciaDTO {
    private Long origemId;
    private Long destinoId;
    private BigDecimal valor;
}
