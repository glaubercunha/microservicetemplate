package br.com.estudo.microservicetemplate.domain.entity;

import java.util.Date;

public record Mensagem (
    Long id,
    Date data,
    String texto,
    Usuario usuario,
    Sala sala){}
