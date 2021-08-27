package br.com.bb.cotacao.client;

import br.com.bb.cotacao.dto.CotacaoWrapperResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CotacaoDolarDiaClient {

    private static final String URL_SERVICO="https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoDolarDia(dataCotacao=@dataCotacao)?@dataCotacao=";
    private static final String COTACAO_="&$top=100&$format=json&$select=cotacaoCompra,cotacaoVenda";
    private static final String data_inicial = "08-20-2021";

    public CotacaoWrapperResponse buscarCotacoes(String data){
        return new RestTemplate().getForObject(URL_SERVICO+"'"+data+"'"+COTACAO_, CotacaoWrapperResponse.class);
    }

}