package br.com.somapay.api;

import br.com.somapay.domain.model.to.TransferenciaDTO;
import br.com.somapay.domain.service.TransferenciaServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaServie servie;

    @PostMapping
    public void criarTransferencia(@RequestBody TransferenciaDTO transferenciaDTO) {
        servie.criarTransferencia(transferenciaDTO);
    }
}
