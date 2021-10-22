package br.com.somapay.domain.repository;

import br.com.somapay.domain.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {


    @Override
    <S extends Transferencia> S save(S entity);
}
