package br.com.somapay.domain.model.enums;

public enum TipoConta {
    EMPRESA("Empresa"),
    FUNCIONARIO("Funcionario");

    final String descricao;

    TipoConta(String descricao) {
        this.descricao = descricao;
    }
}
