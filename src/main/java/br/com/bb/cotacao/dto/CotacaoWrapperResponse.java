package br.com.bb.cotacao.dto;


import br.com.bb.cotacao.model.Cotacao;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("CotacaoWrapperResponse")
public class CotacaoWrapperResponse {

    @ApiModelProperty("Lista de cotações")
    private List<Cotacao> value;


}