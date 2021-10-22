package br.com.somapay.domain.service;

import br.com.somapay.domain.model.TransferenciaOutroEmpresa;
import br.com.somapay.domain.repository.TransferenciaOutroEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaOutroEmpresaService {

    @Autowired
    private TransferenciaOutroEmpresaRepository repository;

    public TransferenciaOutroEmpresa salvarTransferencia(TransferenciaOutroEmpresa transferenciaOutroEmpresa) {
        return repository.save(transferenciaOutroEmpresa);
    }
}
