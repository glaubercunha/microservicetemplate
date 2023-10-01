package br.com.estudo.microservicetemplate.domain.repository.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.estudo.microservicetemplate.domain.entity.Mensagem;
import br.com.estudo.microservicetemplate.domain.mapper.MensagemMapper;
import br.com.estudo.microservicetemplate.domain.repository.MensagemRepository;

@Component
public class MensagemRepositoryImpl implements MensagemRepository{
    
    private final String FIND_BY_SALA_ID = 
    "SELECT m.id, m.data, m.texto, m.sala_id, m.usuario_id, u.nome"
        + " FROM mensagem m JOIN usuario u ON m.usuario_id = u.id"
        + " WHERE m.sala_id = ?"
        + " ORDER BY m.data";

    private JdbcTemplate jdbcTemplate;

    public MensagemRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Mensagem> findBySalaId(Long salaId) {

        List<Mensagem> mensagens = jdbcTemplate.query(
            FIND_BY_SALA_ID,
            new MensagemMapper(),
            salaId);
        
        return mensagens;
    }
}
