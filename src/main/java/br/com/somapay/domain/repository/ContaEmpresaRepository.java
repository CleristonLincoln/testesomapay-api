package br.com.somapay.domain.repository;

import br.com.somapay.domain.model.ContaEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaEmpresaRepository extends JpaRepository<ContaEmpresa, Long> {
}
