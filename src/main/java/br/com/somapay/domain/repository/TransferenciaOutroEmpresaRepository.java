package br.com.somapay.domain.repository;

import br.com.somapay.domain.model.TransferenciaOutroEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaOutroEmpresaRepository extends JpaRepository<TransferenciaOutroEmpresa, Long> {
}
