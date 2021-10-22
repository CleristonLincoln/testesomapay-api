package br.com.somapay.domain.service;

import br.com.somapay.domain.model.ContaFuncionario;
import br.com.somapay.domain.model.enums.TipoConta;
import br.com.somapay.domain.model.to.TransferenciaDTO;
import br.com.somapay.domain.repository.ContaFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ContaFuncionarioService {

    @Autowired
    private ContaFuncionarioRepository repository;

    @Autowired
    private FuncionarioService funcionarioService;

    public ContaFuncionario criarNovaContaFuncionario(Long id){

        ContaFuncionario contaFuncionario = new ContaFuncionario();
        contaFuncionario.setFuncionario(funcionarioService.buscarFuncionario(id).getBody());
        contaFuncionario.setTipoConta(TipoConta.FUNCIONARIO);
        contaFuncionario.setSaldo(BigDecimal.ZERO);
        contaFuncionario.setDataCriacao(LocalDateTime.now());

        return repository.save(contaFuncionario);
    }

    public void acrescentarSaldo(ContaFuncionario contaFuncionario, BigDecimal valor) {

        BigDecimal saldo = contaFuncionario.getSaldo().add(valor);
        contaFuncionario.setSaldo(saldo);

        repository.save(contaFuncionario);
    }

    public void retirarSaldo(ContaFuncionario contaFuncionario, BigDecimal valor) {

        BigDecimal saldo = contaFuncionario.getSaldo().subtract(valor);
        contaFuncionario.setSaldo(saldo);

        repository.save(contaFuncionario);
    }
}
