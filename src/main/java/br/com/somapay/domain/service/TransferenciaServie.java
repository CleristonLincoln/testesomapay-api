package br.com.somapay.domain.service;

import br.com.somapay.domain.exceptions.SaldoException;
import br.com.somapay.domain.model.Empresa;
import br.com.somapay.domain.model.Funcionario;
import br.com.somapay.domain.model.TransferenciaEmpresaFuncionario;
import br.com.somapay.domain.model.TransferenciaFuncionarioOutro;
import br.com.somapay.domain.model.TransferenciaOutroEmpresa;
import br.com.somapay.domain.model.to.TransferenciaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransferenciaServie {

    @Autowired
    private TransferenciaOutroEmpresaService transferenciaOutroEmpresaService;

    @Autowired
    private TransferenciaEmpresaFuncionarioService transferenciaEmpresaFuncionarioService;

    @Autowired
    private TransferenciaFuncionarioOutroService transferenciaFuncionarioOutroService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ContaEmpresaService contaEmpresaService;

    @Autowired
    private ContaFuncionarioService contaFuncionarioService;

    /**
     * <p>Se origem for null -> injetar saldo na empresa</p>
     * <p>Se destino for null -> funcionario esta sacando o dinheiro</p>
     * <p>Se destino e origem não for null -> transferencia de empresa para funcionario</p>
     *
     * @param transferenciaDTO {@link TransferenciaDTO}
     */
    public void criarTransferencia(TransferenciaDTO transferenciaDTO) {

        if (transferenciaDTO.getOrigemId() == null)
            gerarSaldoEmpresa(transferenciaDTO);

        if (transferenciaDTO.getDestinoId() == null)
            transferirFuncionarioOutro(transferenciaDTO);

        if (transferenciaDTO.getOrigemId() != null && transferenciaDTO.getDestinoId() != null)
            transferirEmpresaFuncionario(transferenciaDTO);

    }

    private void transferirEmpresaFuncionario(TransferenciaDTO transferenciaDTO) {
        Empresa empresa = empresaService.validarSeEmpresaExixte(transferenciaDTO.getOrigemId());

        Funcionario funcionario = funcionarioService.validarSeFuncionarioExiste(transferenciaDTO.getDestinoId());
        validarSeEmpresaPossuiSaldo(empresa, transferenciaDTO.getValor());
        TransferenciaEmpresaFuncionario transferenciaEmpresaFuncionario = new TransferenciaEmpresaFuncionario(
                empresa,
                funcionario,
                "Transferencia de saldo: " + empresa.getCnpj() + " para "+ funcionario.getCpf(),
                LocalDateTime.now(),
                empresa.getContaEmpresa(),
                funcionario.getContaFuncionario(),
                transferenciaDTO.getValor()
        );

        transferenciaEmpresaFuncionarioService.salvarTransferencia(transferenciaEmpresaFuncionario);

        contaEmpresaService.retiradaSaldo(empresa.getContaEmpresa(), transferenciaDTO.getValor());
        contaFuncionarioService.acrescentarSaldo(funcionario.getContaFuncionario(), transferenciaDTO.getValor());
    }

    private void transferirFuncionarioOutro(TransferenciaDTO transferenciaDTO) {
        Funcionario funcionario = funcionarioService.validarSeFuncionarioExiste(transferenciaDTO.getOrigemId());

        validarSeFuncionarioPossuiSaldo(funcionario, transferenciaDTO.getValor());

        TransferenciaFuncionarioOutro transferenciaOutroEmpresa = new TransferenciaFuncionarioOutro(
                funcionario,
                "Saque Caixa pelo funcionario",
                LocalDateTime.now(),
                funcionario.getContaFuncionario(),
                null,
                transferenciaDTO.getValor()
        );
        transferenciaFuncionarioOutroService.salvarTransferencia(transferenciaOutroEmpresa);
        contaFuncionarioService.retirarSaldo(funcionario.getContaFuncionario(), transferenciaDTO.getValor());
    }

    private void validarSeFuncionarioPossuiSaldo(Funcionario funcionario, BigDecimal valor) {
        if(funcionario.getContaFuncionario().getSaldo().compareTo(valor) <= 0)
            throw new SaldoException("Funcionário não possui saldo para realizar a transação");
    }

    private void validarSeEmpresaPossuiSaldo(Empresa empresa, BigDecimal valor) {
        if(empresa.getContaEmpresa().getSaldo().compareTo(valor) <= 0)
            throw new SaldoException("Empresa não possui saldo para realizar a transação");
    }

    private void gerarSaldoEmpresa(TransferenciaDTO transferenciaDTO) {
        Empresa empresa = empresaService.buscarEmpresa(transferenciaDTO.getDestinoId()).getBody();
        TransferenciaOutroEmpresa transferenciaOutroEmpresa = new TransferenciaOutroEmpresa(
                empresa,
                "Saldo para empresa",
                LocalDateTime.now(),
                null,
                empresa.getContaEmpresa(),
                transferenciaDTO.getValor()
                );

        transferenciaOutroEmpresaService.salvarTransferencia(transferenciaOutroEmpresa);

        contaEmpresaService.acrescentarSaldo(empresa.getContaEmpresa(), transferenciaDTO.getValor());
    }

}
