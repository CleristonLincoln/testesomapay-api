package br.com.somapay.domain.service;

import br.com.somapay.domain.exceptions.EntidadeEmUsoException;
import br.com.somapay.domain.exceptions.EntidadeJaExiste;
import br.com.somapay.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.somapay.domain.model.ContaEmpresa;
import br.com.somapay.domain.model.Empresa;
import br.com.somapay.domain.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void excluir(Long cozinhaId) {
        try {
            repository.deleteById(cozinhaId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de empresa com código %d", cozinhaId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Empresa de código %d não pode ser removida, pois está em uso", cozinhaId));
        }
    }


    public ResponseEntity<Empresa> buscarEmpresa(Long id) {

        Empresa empresa = repository.findById(id).orElse(null);
        if (empresa == null) {
            throw new EntidadeNaoEncontradaException("Empresa não encontrada com o id: " + id);
        }
        return ResponseEntity.ok(empresa);
    }
}
