package br.com.tgolopes.sociotorcedor.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.tgolopes.SocioTorcedorApplication;
import br.com.tgolopes.controller.request.SocioTorcedorRequest;
import br.com.tgolopes.entity.SocioTorcedor;
import br.com.tgolopes.sociotorcedor.mock.SocioTorcedorRequestMock;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {SocioTorcedorApplication.class})
public class SocioTorcedorRestTest {

    private static final String BASE_URL = "http://localhost:8082/v1/torcedor/";

    @Autowired
    private TestRestTemplate socioTorcedorRestTemplate;
    
    private HttpEntity<String> httpEntity;

    @Before
    public void init() {
        MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<>();
        requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        this.httpEntity = new HttpEntity<>(requestHeaders);
    }

    @Test
    public void deveConsultarTodosSocioTorcedores() {
        ResponseEntity<List<SocioTorcedor>> responseEntity = socioTorcedorRestTemplate.exchange(BASE_URL,
                HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<SocioTorcedor>>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertFalse(responseEntity.getBody().isEmpty());
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
   
    @Test
    public void deveConsultarSocioTorcedorPorId() {
        String url = BASE_URL.concat("1");

        ResponseEntity<SocioTorcedor> responseEntity = socioTorcedorRestTemplate.exchange(
                url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<SocioTorcedor>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
    
    @Test
    public void deveRetornarHttp404AoConsultarSocioTorcedorPorIdQueNaoExiste() {
        String url = BASE_URL.concat("4651");

        ResponseEntity<SocioTorcedor> responseEntity = socioTorcedorRestTemplate.exchange(
                url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<SocioTorcedor>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 404);
    }

    @Test
    public void deveIncluirSocioTorcedorComSucesso() {
    	SocioTorcedorRequest socioTorcedorRequest = SocioTorcedorRequestMock.getSocioTorcedorRequest();

        ResponseEntity<SocioTorcedor> responseEntity =
                socioTorcedorRestTemplate.postForEntity(BASE_URL, socioTorcedorRequest, SocioTorcedor.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
    
    @Test
    public void deveRetornarHttp400AoIncluirSocioTorcedorComNomeNulo() {
        SocioTorcedorRequest socioTorcedorRequest = SocioTorcedorRequestMock.getSocioTorcedorRequest();
        socioTorcedorRequest.setNome(null);

        ResponseEntity<SocioTorcedor> responseEntity =
                socioTorcedorRestTemplate.postForEntity(BASE_URL, socioTorcedorRequest, SocioTorcedor.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoIncluirSocioTorcedorComTimeCoracaoNulo() {
        SocioTorcedorRequest socioTorcedorRequest = SocioTorcedorRequestMock.getSocioTorcedorRequest();
        socioTorcedorRequest.setTimeCoracao(null);

        ResponseEntity<SocioTorcedor> responseEntity =
                socioTorcedorRestTemplate.postForEntity(BASE_URL, socioTorcedorRequest, SocioTorcedor.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoIncluirSocioTorcedorComDataNascimentoNulo() {
        SocioTorcedorRequest socioTorcedorRequest = SocioTorcedorRequestMock.getSocioTorcedorRequest();
        socioTorcedorRequest.setDataNascimento(null);

        ResponseEntity<SocioTorcedor> responseEntity =
                socioTorcedorRestTemplate.postForEntity(BASE_URL, socioTorcedorRequest, SocioTorcedor.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoIncluirSocioTorcedorComEmailNulo() {
        SocioTorcedorRequest socioTorcedorRequest = SocioTorcedorRequestMock.getSocioTorcedorRequest();
        socioTorcedorRequest.setEmail(null);

        ResponseEntity<SocioTorcedor> responseEntity =
                socioTorcedorRestTemplate.postForEntity(BASE_URL, socioTorcedorRequest, SocioTorcedor.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoAlterarSocioTorcedorComNomeNulo()throws URISyntaxException {
    	String url = BASE_URL.concat("1");
        SocioTorcedorRequest socioTorcedorRequest = SocioTorcedorRequestMock.getSocioTorcedorRequest();
        socioTorcedorRequest.setNome(null);

        ResponseEntity<SocioTorcedor> responseEntity = socioTorcedorRestTemplate.exchange(
                url,
                HttpMethod.PUT, RequestEntity.put(new URI(url)).body(socioTorcedorRequest),
                new ParameterizedTypeReference<SocioTorcedor>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoAlterarSocioTorcedorComTimeCoracaoNulo() throws URISyntaxException{
    	String url = BASE_URL.concat("1");
        SocioTorcedorRequest socioTorcedorRequest = SocioTorcedorRequestMock.getSocioTorcedorRequest();
        socioTorcedorRequest.setTimeCoracao(null);

        ResponseEntity<SocioTorcedor> responseEntity = socioTorcedorRestTemplate.exchange(
                url,
                HttpMethod.PUT, RequestEntity.put(new URI(url)).body(socioTorcedorRequest),
                new ParameterizedTypeReference<SocioTorcedor>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoAlterarSocioTorcedorComDataNscimentoCoracaoNulo() throws URISyntaxException{
    	String url = BASE_URL.concat("1");
        SocioTorcedorRequest socioTorcedorRequest = SocioTorcedorRequestMock.getSocioTorcedorRequest();
        socioTorcedorRequest.setDataNascimento(null);

        ResponseEntity<SocioTorcedor> responseEntity = socioTorcedorRestTemplate.exchange(
                url,
                HttpMethod.PUT, RequestEntity.put(new URI(url)).body(socioTorcedorRequest),
                new ParameterizedTypeReference<SocioTorcedor>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoAlterarSocioTorcedorComEmailNulo() throws URISyntaxException{
    	String url = BASE_URL.concat("1");
        SocioTorcedorRequest socioTorcedorRequest = SocioTorcedorRequestMock.getSocioTorcedorRequest();
        socioTorcedorRequest.setEmail(null);

        ResponseEntity<SocioTorcedor> responseEntity = socioTorcedorRestTemplate.exchange(
                url,
                HttpMethod.PUT, RequestEntity.put(new URI(url)).body(socioTorcedorRequest),
                new ParameterizedTypeReference<SocioTorcedor>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
   
    
    @Test
    public void deveExcluirPorId() throws URISyntaxException {
        String url = BASE_URL.concat("2");

        ResponseEntity<SocioTorcedor> responseEntity = socioTorcedorRestTemplate.exchange(url, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<SocioTorcedor>() {});

        Assert.assertNull(responseEntity.getBody());
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
}
