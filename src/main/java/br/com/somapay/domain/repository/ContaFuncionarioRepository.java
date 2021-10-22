package br.com.somapay.domain.repository;

import br.com.somapay.domain.model.ContaFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaFuncionarioRepository extends JpaRepository<ContaFuncionario, Long> {
}
