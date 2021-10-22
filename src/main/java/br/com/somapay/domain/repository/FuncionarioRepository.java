package br.com.somapay.domain.repository;

import br.com.somapay.domain.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findByNomeContains(String nome);

    @Query(value = "SELECT f.id," +
            " f.nome, " +
            " f.cpf, " +
            "c.saldo, " +
            " f.id_empresa " +
            " FROM base.funcionario f " +
            "                  INNER JOIN financeiro.conta_funcionario cf ON f.conta_id = cf.conta_id " +
            "                  INNER JOIN financeiro.conta c ON cf.conta_id = c.id", nativeQuery = true)
    List<Object[]> buscarFuncionariosResumido();
}
