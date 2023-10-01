package br.com.estudo.microservicetemplate.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import br.com.estudo.microservicetemplate.domain.entity.Mensagem;
import br.com.estudo.microservicetemplate.domain.entity.Sala;
import br.com.estudo.microservicetemplate.domain.entity.Usuario;

public class MensagemMapper implements RowMapper<Mensagem>{

    @Override
    @Nullable
    public Mensagem mapRow(ResultSet rs, int rowNum) throws SQLException {

        Mensagem mensagem = new Mensagem(
            rs.getLong("id"),
            rs.getTimestamp("data"),
            rs.getString("texto"),
            new Usuario(rs.getLong("sala_id"), rs.getString("nome")),
            new Sala(rs.getLong("sala_id"), null));

        return mensagem;
    }
    
}
