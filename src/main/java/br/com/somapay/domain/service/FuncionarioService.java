package br.com.somapay.domain.service;

import br.com.somapay.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.somapay.domain.model.ContaFuncionario;
import br.com.somapay.domain.model.Funcionario;
import br.com.somapay.domain.repository.FuncionarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private ContaFuncionarioService contaFuncionarioService;

    @Autowired
    private EmpresaService empresaService;

    public List<Funcionario> listarTodosFuncionarios() {
        return repository.findAll();
    }

    public Funcionario validarSeFuncionarioExiste(Long id){
        return repository.findById(id).orElseThrow(()->
                new EntidadeNaoEncontradaException("Não foi possivel localizar o funcionário"));
    }

    public ResponseEntity<Funcionario> buscarFuncionario(Long id) {

        return ResponseEntity.ok(validarSeFuncionarioExiste(id));
    }

    public Funcionario salvar(Funcionario funcionario) {

        empresaService.buscarEmpresa(funcionario.getEmpresa().getId());


     // converte cpf para sem caracteres especiais e garante que todas as letras do nome estaram em letras de forma
        String novoCpf = funcionario.getCpf()
                .replace(".", "")
                .replace("-", "");
        String novoNome = funcionario.getNome().toUpperCase(Locale.ROOT);

        funcionario.setCpf(novoCpf);
        funcionario.setNome(novoNome);

        Funcionario funcionarioSalvo = repository.save(funcionario);

        // criar conta automaticamente
        funcionarioSalvo.setContaFuncionario(contaFuncionarioService.criarNovaContaFuncionario(funcionario.getId()));

        return funcionario;
    }
}
