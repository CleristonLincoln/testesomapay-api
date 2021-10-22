package br.com.somapay.domain.repository;

import br.com.somapay.domain.model.TransferenciaFuncionarioOutro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaFuncionarioOutroRepository extends JpaRepository<TransferenciaFuncionarioOutro, Long> {
}
