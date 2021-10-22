package br.com.somapay.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "financeiro", name = "conta_empresa")
@PrimaryKeyJoinColumn(name = "conta_id")
public class ContaEmpresa extends Conta {

    @OneToOne
    @JoinColumn(nullable = false, name = "empresa_id")
    private Empresa empresa;


    private LocalDateTime dataCriacao;
}
