package br.com.estudo.microservicetemplate.domain.dto;

import java.util.Date;

public record MensagemDTO (
    Long id,
    Date data,
    String texto,
    Long usuarioId,
    String usuarioNome,
    Long salaId){}