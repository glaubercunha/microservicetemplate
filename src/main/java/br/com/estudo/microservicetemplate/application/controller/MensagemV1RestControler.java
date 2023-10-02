package br.com.estudo.microservicetemplate.application.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudo.microservicetemplate.domain.dto.MensagemDTO;
import br.com.estudo.microservicetemplate.domain.entity.Mensagem;
import br.com.estudo.microservicetemplate.domain.mapper.MensagemMapper;
import br.com.estudo.microservicetemplate.domain.service.MensagemService;

@RestController
@RequestMapping("/api/mensagem/v1")
public class MensagemV1RestControler {
    
  @Autowired
  private MensagemService service;

  public MensagemV1RestControler(MensagemService service){
    this.service = service;
  }
    
  @GetMapping("/mensagens/{salaid}")
  @Cacheable(value = "MensagensBySalaId", key = "#salaid")
  public List<MensagemDTO> findBySalaId(@PathVariable("salaid") Long salaid) {
    
    List<Mensagem> mensagens = this.service.findBySalaId(salaid);

    List<MensagemDTO> mensagensDTO = mensagens.stream()
      .map(m -> new MensagemMapper().mapDTO(m))
      .collect(Collectors.toList());

    return mensagensDTO;
  }
}
