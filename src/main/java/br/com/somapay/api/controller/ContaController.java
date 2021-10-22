package br.com.somapay.api.controller;

import br.com.somapay.domain.model.ContaEmpresa;
import br.com.somapay.domain.model.ContaFuncionario;
import br.com.somapay.domain.repository.ContaEmpresaRepository;
import br.com.somapay.domain.repository.ContaFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("conta")
public class ContaController {

    @Autowired
    private ContaFuncionarioRepository contaFuncionarioRepository;

    @Autowired
    private ContaEmpresaRepository contaEmpresaRepository;


    @GetMapping("funcionario")
    public List<ContaFuncionario> contasFuncionario() {
        return contaFuncionarioRepository.findAll();
    }

    @GetMapping("empresa")
    public List<ContaEmpresa> contasEmpresa() {
        return contaEmpresaRepository.findAll();
    }

}
