package br.com.estudo.microservicetemplate.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MensagemV1RestControlerITest {
    
    private static final String MENSAGEM_V1_MENSAGENS_SALA_ID = "/mensagem/v1/mensagens?salaId=";
    @LocalServerPort
    private Integer port;
    private TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void findBySalaIdHttpStatusOK() throws JSONException{

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort(MENSAGEM_V1_MENSAGENS_SALA_ID + 2),
            HttpMethod.GET, new HttpEntity<>(null, headers), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void findBySalaIdEmpty() throws JSONException{
        
        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort(MENSAGEM_V1_MENSAGENS_SALA_ID + 100),
            HttpMethod.GET, new HttpEntity<>(null, headers), String.class);

        String expected = "[]";
        JSONAssert.assertEquals(expected, response.getBody(), false);   
    }

    @Test
    public void findBySalaIdExist() throws JSONException{

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort(MENSAGEM_V1_MENSAGENS_SALA_ID + 2),
            HttpMethod.GET, new HttpEntity<>(null, headers), String.class);

        String expected = "[{\"id\":3,\"data\":\"2023-09-29T18:12:46.242+00:00\",\"texto\":\"Oi, falou com Glauber?\",\"usuario\":{\"id\":2,\"nome\":\"João\"},\"sala\":{\"id\":2,\"nome\":null}},{\"id\":4,\"data\":\"2023-09-29T18:13:59.861+00:00\",\"texto\":\"Oi, ainda não\",\"usuario\":{\"id\":2,\"nome\":\"Maria\"},\"sala\":{\"id\":2,\"nome\":null}}]";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}