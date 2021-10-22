package br.com.somapay.domain.service;

import br.com.somapay.domain.exceptions.EntidadeEmUsoException;
import br.com.somapay.domain.exceptions.EntidadeJaExiste;
import br.com.somapay.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.somapay.domain.model.Empresa;
import br.com.somapay.domain.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository repository;

    @Autowired
    private ContaEmpresaService contaEmpresaService;


    public Empresa salvar(Empresa empresa) {

        validacao(empresa);

        Empresa empresaSalva = repository.save(empresa);

        // cria automaticamente a conta da empresa
        empresaSalva.setContaEmpresa(contaEmpresaService.criarNovaContaEmpresa(empresa.getId()));

        return repository.save(empresa);
    }

    private void validacao(Empresa empresa) {
        if (repository.findByCnpjExists(empresa.getCnpj())) {
            throw new EntidadeJaExiste("Já existe um cadastro para o CNPJ informado.");
        }
    }


    public ResponseEntity<Empresa> buscarEmpresa(Long id) {
        return ResponseEntity.ok(validarSeEmpresaExixte(id));
    }


    public Empresa validarSeEmpresaExixte(Long id){
       return repository.findById(id).orElseThrow(() ->
               new EntidadeNaoEncontradaException("Empresa não encontrada com o id: " + id));
    }
}
