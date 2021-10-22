package br.com.somapay.api.controller;

import br.com.somapay.domain.model.Transferencia;
import br.com.somapay.domain.model.to.TransferenciaDTO;
import br.com.somapay.domain.repository.TransferenciaRepository;
import br.com.somapay.domain.service.TransferenciaServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaRepository repository;

    @Autowired
    private TransferenciaServie servie;

    @GetMapping
    public List<Transferencia> buscarTodas(){
        return repository.findAll();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void criarTransferencia(@RequestBody TransferenciaDTO transferenciaDTO) {
        servie.criarTransferencia(transferenciaDTO);
    }
}
