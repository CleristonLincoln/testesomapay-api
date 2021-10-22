package br.com.somapay.domain.service;

import br.com.somapay.domain.model.TransferenciaEmpresaFuncionario;
import br.com.somapay.domain.repository.TransferenciaEmpresaFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaEmpresaFuncionarioService {

    @Autowired
    private TransferenciaEmpresaFuncionarioRepository repository;

    public void salvarTransferencia(TransferenciaEmpresaFuncionario transferenciaEmpresaFuncionario) {
        repository.save(transferenciaEmpresaFuncionario);
    }
}
