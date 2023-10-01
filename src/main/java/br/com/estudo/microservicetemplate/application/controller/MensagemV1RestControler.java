package br.com.estudo.microservicetemplate.application.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudo.microservicetemplate.domain.dto.MensagemDTO;
import br.com.estudo.microservicetemplate.domain.entity.Mensagem;
import br.com.estudo.microservicetemplate.domain.mapper.MensagemMapper;
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
  public List<MensagemDTO> findBySalaId(Long salaId) {
    
    List<Mensagem> mensagens = this.service.findBySalaId(salaId);

    List<MensagemDTO> mensagensDTO = mensagens.stream()
      .map(m -> new MensagemMapper().mapDTO(m))
      .collect(Collectors.toList());

    return mensagensDTO;
  }
}
