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

    @Modifying
    @Query(value = "UPDATE base.funcionario SET conta_id = :contaId WHERE id=:funcionarioId", nativeQuery = true)
    void updateconta(Long funcionarioId, Long contaId);
}
