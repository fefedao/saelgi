package br.com.battycode.dao.impl;

import br.com.battycode.dao.LicitacaoDAO;
import br.com.battycode.dto.Licitacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by fefedo on 13/04/16.
 */
@Repository
public class LicitacaoDAOImpl implements LicitacaoDAO{

    @Autowired
    private DataSource datasource;

    @Override
    public List<Licitacao> findAll() {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        return jdbctemplate.query("SELECT * FROM licitacao", new RowMapper<Licitacao>() {
            @Override
            public Licitacao mapRow(ResultSet resultSet, int i) throws SQLException {
                Licitacao licitacao = new Licitacao();
                licitacao.setCodigo(resultSet.getInt("codigo"));
                licitacao.setCodigoOrgao(resultSet.getInt("codigoOrgao"));
                licitacao.setCodigoModalidade(resultSet.getInt("codigoModalidade"));
                licitacao.setNumeroEdital(resultSet.getString("numeroEdital"));
                licitacao.setDataDeAbertura(resultSet.getDate("dataDeAbertura"));
                licitacao.setDataEntregaDocumentacao(resultSet.getDate("dataEntregaDocumentacao"));
                licitacao.setDataEntregaProposta(resultSet.getDate("dataEntregaProposta"));
                return licitacao;
            }
        });
    }
}
