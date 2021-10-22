package br.com.somapay.domain.service;

import br.com.somapay.domain.model.TransferenciaFuncionarioOutro;
import br.com.somapay.domain.repository.TransferenciaFuncionarioOutroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaFuncionarioOutroService {

    @Autowired
    private TransferenciaFuncionarioOutroRepository repository;
    public void salvarTransferencia(TransferenciaFuncionarioOutro transferenciaOutroEmpresa) {
        repository.save(transferenciaOutroEmpresa);
    }
}
