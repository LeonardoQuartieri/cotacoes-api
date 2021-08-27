package br.com.bb.cotacao.service;

import br.com.bb.cotacao.client.CotacaoDolarDiaClient;
import br.com.bb.cotacao.dto.CotacaoWrapperResponse;
import br.com.bb.cotacao.model.Cotacao;
import br.com.bb.cotacao.repository.CotacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@org.springframework.stereotype.Service
public class CotacaoService {

    @Autowired
    private CotacaoDolarDiaClient cotacaoDolarDiaClient;

    @Autowired
    private CotacoesRepository repo;

    public CotacaoWrapperResponse buscarCotacoesPorData(String data){
        CotacaoWrapperResponse retorno = cotacaoDolarDiaClient.buscarCotacoes(data);
        Cotacao cotacaoRetorno = null;
        if(retorno.getValue()!=null){
            cotacaoRetorno = retorno.getValue().get(0);
            cotacaoRetorno.setDataCotacaoDolar(data);
            cotacaoRetorno.setTimestampRequisicao(LocalDateTime.now());
            repo.save(cotacaoRetorno);
        }

        return retorno;
    }

    public List<Cotacao> buscarCotacoes(){

        return  repo.findAll();
    }
}