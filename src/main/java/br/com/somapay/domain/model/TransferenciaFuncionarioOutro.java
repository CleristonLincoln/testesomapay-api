package br.com.somapay.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@Entity
@Table(schema = "financeiro", name = "transferencia_funcionario_outro")
@PrimaryKeyJoinColumn(name = "transferencia_id")
public class TransferenciaFuncionarioOutro extends Transferencia {

    @ManyToOne
    @JoinColumn(nullable = false, name = "funcionario_id")
    private Funcionario funcionario;
}
