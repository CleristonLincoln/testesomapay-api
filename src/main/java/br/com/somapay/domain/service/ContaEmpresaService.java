package br.com.somapay.domain.service;

import br.com.somapay.domain.model.ContaEmpresa;
import br.com.somapay.domain.model.enums.TipoConta;
import br.com.somapay.domain.repository.ContaEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ContaEmpresaService {

    @Autowired
    private ContaEmpresaRepository repository;

    @Autowired
    private ContaEmpresaRepository contaEmpresaRepository;

    @Autowired
    private EmpresaService empresaService;


    public ContaEmpresa criarNovaContaEmpresa(Long id){

        ContaEmpresa contaEmpresa = new ContaEmpresa();
        contaEmpresa.setEmpresa(empresaService.buscarEmpresa(id).getBody());
        contaEmpresa.setTipoConta(TipoConta.EMPRESA);
        contaEmpresa.setSaldo(BigDecimal.ZERO);
        contaEmpresa.setDataCriacao(LocalDateTime.now());

        return repository.save(contaEmpresa);
    }

    public void acrescentarSaldo(ContaEmpresa contaEmpresa, BigDecimal valor) {

        BigDecimal saldo = contaEmpresa.getSaldo().add(valor);
        contaEmpresa.setSaldo(saldo);

        contaEmpresaRepository.save(contaEmpresa);
    }

    public void retiradaSaldo(ContaEmpresa contaEmpresa, BigDecimal valor) {

        BigDecimal saldo = contaEmpresa.getSaldo().subtract(valor);
        contaEmpresa.setSaldo(saldo);

        contaEmpresaRepository.save(contaEmpresa);
    }
}
