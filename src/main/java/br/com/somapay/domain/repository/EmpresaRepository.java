package br.com.somapay.domain.repository;

import br.com.somapay.domain.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Query(value = "SELECT EXISTS(SELECT * FROM base.empresa e WHERE e.cnpj = :cnpj)", nativeQuery = true)
    Boolean findByCnpjExists(String cnpj);
}
