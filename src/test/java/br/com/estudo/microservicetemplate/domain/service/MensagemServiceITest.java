package br.com.estudo.microservicetemplate.domain.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.estudo.microservicetemplate.domain.entity.Mensagem;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MensagemServiceITest {
    
    @Autowired
    private MensagemService service;

    @Test
    public void findBySalaIdExist(){
        List<Mensagem> findBySalaId = service.findBySalaId(1l);
        assertFalse(findBySalaId.isEmpty());
    }

    @Test
    public void findBySalaIdNotExist(){
        List<Mensagem> findBySalaId = service.findBySalaId(100l);
        assertTrue(findBySalaId.isEmpty());
    }
}
