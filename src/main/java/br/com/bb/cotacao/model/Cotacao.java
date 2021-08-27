package br.com.bb.cotacao.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Cotacao")
@Entity
public class Cotacao  {

    @ApiModelProperty("Identificador da requisicao")
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @ApiModelProperty("TimeStamp da requisição")
    @Column(name = "timestamp_requisicao")
    private LocalDateTime timestampRequisicao;

    @ApiModelProperty("data da cotação")
    @Column(name = "data_cotacao_dolar")
    private String dataCotacaoDolar;

    @ApiModelProperty("Valor de compra")
    @Column(name = "valor_compra")
    @JsonProperty("cotacaoCompra")
    private Double valorCompra;

    @ApiModelProperty("Valor de venda")
    @Column(name = "valor_venda")
    @JsonProperty("cotacaoVenda")
    private Double valorVenda;


}