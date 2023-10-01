package br.com.estudo.microservicetemplate.domain.repository;

import java.util.List;

import br.com.estudo.microservicetemplate.domain.entity.Mensagem;

public interface MensagemRepository {
        public List<Mensagem> findBySalaId(Long salaId);
 
}
