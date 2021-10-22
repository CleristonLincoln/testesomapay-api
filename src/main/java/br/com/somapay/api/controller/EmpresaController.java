package br.com.somapay.api;

import br.com.somapay.domain.model.Empresa;
import br.com.somapay.domain.repository.EmpresaRepository;
import br.com.somapay.domain.service.EmpresaService;
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
@RequestMapping("empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService service;

    @Autowired
    private EmpresaRepository repository;

    @GetMapping
    public List<Empresa> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Empresa> getById(@PathVariable Long id) {
        return service.buscarEmpresa(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa salvarEmrpesa(@RequestBody @Valid Empresa empresa) {
        return service.salvar(empresa);
    }

}
