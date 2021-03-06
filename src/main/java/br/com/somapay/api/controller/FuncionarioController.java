package br.com.somapay.api.controller;

import br.com.somapay.domain.model.Funcionario;
import br.com.somapay.domain.model.to.FuncionariosResumidoTO;
import br.com.somapay.domain.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;


    @GetMapping
    public List<Funcionario> getAllFuncionarios(){
        return service.listarTodosFuncionarios();
    }

    @GetMapping("resumo")
    public List<FuncionariosResumidoTO> getAllFuncionariosResumido(){
        return service.listarTodosFuncionariosResumido();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarFuncionario(@PathVariable Long id){
        return service.buscarFuncionario(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario salvar(@RequestBody @Valid  Funcionario funcionario){
        return service.salvar(funcionario);
    }


}
