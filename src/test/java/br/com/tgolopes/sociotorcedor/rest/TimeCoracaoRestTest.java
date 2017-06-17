package br.com.tgolopes.sociotorcedor.rest;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.tgolopes.SocioTorcedorApplication;
import br.com.tgolopes.entity.TimeCoracao;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {SocioTorcedorApplication.class})
public class TimeCoracaoRestTest {

    private static final String BASE_URL = "http://localhost:8082/v1/time/";

    @Autowired
    private TestRestTemplate timeCoracaoRestTemplate;
    
    private HttpEntity<String> httpEntity;

    @Before
    public void init() {
        MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<>();
        requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        this.httpEntity = new HttpEntity<>(requestHeaders);
    }

    @Test
    public void deveConsultarTodosTimeCoracaoes() {
        ResponseEntity<List<TimeCoracao>> responseEntity = timeCoracaoRestTemplate.exchange(BASE_URL,
                HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TimeCoracao>>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertFalse(responseEntity.getBody().isEmpty());
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
   
    @Test
    public void deveConsultarTimeCoracaoPorId() {
        String url = BASE_URL.concat("1");

        ResponseEntity<TimeCoracao> responseEntity = timeCoracaoRestTemplate.exchange(
                url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<TimeCoracao>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
    
    @Test
    public void deveRetornarHttp404AoConsultarTimeCoracaoPorIdQueNaoExiste() {
        String url = BASE_URL.concat("4651");

        ResponseEntity<TimeCoracao> responseEntity = timeCoracaoRestTemplate.exchange(
                url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<TimeCoracao>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 404);
    }
    
    @Test
    public void deveConsultarTimeCoracaoPorTime() {
        String url = BASE_URL.concat("nome/Portuguesa");

        ResponseEntity<TimeCoracao> responseEntity = timeCoracaoRestTemplate.exchange(
                url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<TimeCoracao>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
    
    @Test
    public void deveRetornarHttp404AoConsultarTimeCoracaoPorTimeQueNaoExiste() {
        String url = BASE_URL.concat("nome/Barcelona");

        ResponseEntity<TimeCoracao> responseEntity = timeCoracaoRestTemplate.exchange(
                url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<TimeCoracao>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 404);
    }
}