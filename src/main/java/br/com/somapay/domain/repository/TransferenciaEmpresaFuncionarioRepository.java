package br.com.somapay.domain.repository;

import br.com.somapay.domain.model.TransferenciaEmpresaFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaEmpresaFuncionarioRepository extends JpaRepository<TransferenciaEmpresaFuncionario, Long> {
}
