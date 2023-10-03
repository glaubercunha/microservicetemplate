package br.com.estudo.microservicetemplate.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudo.microservicetemplate.domain.entity.Mensagem;
import br.com.estudo.microservicetemplate.domain.repository.MensagemRepository;

@Service
public class MensagemService {

    @Autowired
    MensagemRepository repository;

    public List<Mensagem> findBySalaId(Long salaId){
        try {
            // TODO Trecho de código somente para teste do Redis, deverá ser removido
             Thread.sleep(2000);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }

        return repository.findBySalaId(salaId);
    }

    public Mensagem findById(Long id) {
        try {
            // TODO Trecho de código somente para teste do Redis, deverá ser removido
             Thread.sleep(2000);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }

        return repository.findById(id);
    }
}
