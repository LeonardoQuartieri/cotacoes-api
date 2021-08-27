package br.com.bb.cotacao.test;

import br.com.bb.cotacao.dto.CotacaoWrapperResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTest {
	public static final String API_COTACOES = "/api/v1/cotacoes/";
	public static final String DATA_VALIDA = "08-20-2021";
	public static final String DATA_INVALIDA = "33-20-2021";

	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testBuscarCotacaoBrasilPorDataValida() {
		CotacaoWrapperResponse cotacoes = this.restTemplate.getForObject(getServico()+ API_COTACOES+DATA_VALIDA, CotacaoWrapperResponse.class);
		assertNotNull(cotacoes.toString());
	}
	@Test
	public void testBuscarCotacaoBrasilPorDataInValida() {
		CotacaoWrapperResponse cotacoes = this.restTemplate.getForObject(getServico()+ API_COTACOES+ DATA_INVALIDA, CotacaoWrapperResponse.class);
		assertTrue(cotacoes.getValue()==null);
	}
	@Test
	public void testValorRetornadoMenorQue100() {
		CotacaoWrapperResponse cotacoes = this.restTemplate.getForObject(getServico()+ API_COTACOES+DATA_VALIDA, CotacaoWrapperResponse.class);
		assertTrue(cotacoes.getValue()!=null && cotacoes.getValue().get(0).getValorCompra().intValue()<100);
	}
	@Test
	public void testValorRetornadoDiferenteDeZero() {
		CotacaoWrapperResponse cotacoes = this.restTemplate.getForObject(getServico()+ API_COTACOES+ DATA_VALIDA, CotacaoWrapperResponse.class);
		assertTrue(cotacoes.getValue()!=null && cotacoes.getValue().get(0).getValorCompra().intValue()!=0);
	}
	@Test
	public void testValorRetornadoMaiorQueZero() {
		CotacaoWrapperResponse cotacoes = this.restTemplate.getForObject(getServico()+ API_COTACOES+ DATA_VALIDA, CotacaoWrapperResponse.class);
		assertTrue(cotacoes.getValue()!=null && cotacoes.getValue().get(0).getValorCompra().intValue()>0);
	}

	private String getServico() {
		return "http://localhost:" + port;
	}

}
