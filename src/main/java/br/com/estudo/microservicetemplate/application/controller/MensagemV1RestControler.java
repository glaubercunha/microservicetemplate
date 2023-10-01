package br.com.estudo.microservicetemplate.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudo.microservicetemplate.domain.entity.Mensagem;
import br.com.estudo.microservicetemplate.domain.entity.Usuario;
import br.com.estudo.microservicetemplate.domain.service.MensagemService;

@RestController
@RequestMapping("/mensagem/v1")
public class MensagemV1RestControler {
    
  @Autowired
  private MensagemService service;

  public MensagemV1RestControler(MensagemService service){
    this.service = service;
  }
    
  @GetMapping("/mensagens")
  @Cacheable(value = "MensagensBySalaId", key = "#salaId")
  public List<Mensagem> findBySalaId(Long salaId) {
    return this.service.findBySalaId(salaId);
  }
}
