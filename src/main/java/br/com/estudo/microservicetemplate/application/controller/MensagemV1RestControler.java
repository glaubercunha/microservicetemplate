package br.com.estudo.microservicetemplate.application.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    
  @GetMapping("/mensagens/{id}")
  @Cacheable(value = "MensagensById", key = "#id")
  public MensagemDTO findById(@PathVariable("id") Long id) {
    
    Mensagem mensagem = this.service.findById(id);

    if(mensagem != null) {
      MensagemDTO mensagensDTO = new MensagemMapper().mapDTO(mensagem);
      return mensagensDTO;
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mensagem não encontrada");
    }
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
