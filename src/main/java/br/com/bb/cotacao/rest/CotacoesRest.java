package br.com.bb.cotacao.rest;

import br.com.bb.cotacao.dto.CotacaoWrapperResponse;
import br.com.bb.cotacao.exception.DataInvalidaException;
import br.com.bb.cotacao.exception.ResourceNotFoundException;
import br.com.bb.cotacao.model.Cotacao;
import br.com.bb.cotacao.service.CotacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CotacoesRest {
    static final String BARRA_VERSAO="/v1";

    @Autowired
    private CotacaoService cotacaoService;


    @ApiOperation(value = "Retorna a cotação do dolar para a data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a cotação do dolar para a data"),
            @ApiResponse(code = 400, message = "Formato da data inválido"),
            @ApiResponse(code = 404, message = "Registro nao encontrado ou nao é valido"),
            @ApiResponse(code = 500, message = "Exceção durante processamento"),
    })
    @GetMapping(BARRA_VERSAO+"/cotacoes/{data}")
    public ResponseEntity<CotacaoWrapperResponse> buscarCotacoesBrasilPorData(@PathVariable(value = "data") String data)
            throws ResourceNotFoundException, DataInvalidaException {
        CotacaoWrapperResponse retorno = null;
        if (validarData(data)){
            retorno =  cotacaoService.buscarCotacoesPorData(data);
        }else{
            throw new DataInvalidaException("Data inválida - formato esperado = MM-dd-yyyy");
        }



        return ResponseEntity.ok().body(retorno);
    }

    private boolean validarData(String data) {
        if(data==null){return false;}
        if (data.trim().equals("")) {
            return false;
        } else {
            SimpleDateFormat sdfrmt = new SimpleDateFormat("MM-dd-yyyy");
            sdfrmt.setLenient(false);
            try {
                Date javaDate = sdfrmt.parse(data);
            } catch (ParseException e) {
                return false;
            }
            return true;
        }
    }

    @ApiOperation(value = "Retorna a lista com todas as cotações")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista com todas as cotações"),
            @ApiResponse(code = 500, message = "Exceção durante processamento")
    })
    @GetMapping(BARRA_VERSAO+"/cotacoes/listar")
    public ResponseEntity<List<Cotacao>> buscarCotacoesBrasil()
            throws ResourceNotFoundException{
        return ResponseEntity.ok().body(cotacaoService.buscarCotacoes());
    }


}