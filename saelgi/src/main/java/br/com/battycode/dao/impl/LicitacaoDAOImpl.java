package br.com.battycode.dao.impl;

import br.com.battycode.dao.LicitacaoDAO;
import br.com.battycode.dto.Licitacao;
import br.com.battycode.dto.Modalidade;
import br.com.battycode.dto.Orgao;
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
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT l.codigo, l.numeroEdital, l.dataDeAbertura, l.dataEntregaDocumentacao, l.dataEntregaProposta, l.codigoModalidade, m.nomeModalidade, l.codigoOrgao, o.nomeOrgao ");
        sb.append("FROM licitacao l ");
        sb.append("JOIN orgao o ON o.codigo = l.codigoOrgao ");
        sb.append("JOIN modalidade m ON m.codigo = l.codigoModalidade ");

        return jdbctemplate.query(sb.toString(), new RowMapper<Licitacao>() {
            @Override
            public Licitacao mapRow(ResultSet resultSet, int i) throws SQLException {
                Licitacao licitacao = new Licitacao();
                licitacao.setCodigo(resultSet.getInt("codigo"));
                licitacao.setNumeroEdital(resultSet.getString("numeroEdital"));
                licitacao.setDataDeAbertura(resultSet.getDate("dataDeAbertura"));
                licitacao.setDataEntregaDocumentacao(resultSet.getDate("dataEntregaDocumentacao"));
                licitacao.setDataEntregaProposta(resultSet.getDate("dataEntregaProposta"));

                Orgao orgao = new Orgao();
                orgao.setCodigo(resultSet.getInt("codigoOrgao"));
                orgao.setNomeOrgao(resultSet.getString("nomeOrgao"));
                licitacao.setOrgao(orgao);

                Modalidade modalidade = new Modalidade();
                modalidade.setCodigo(resultSet.getInt("codigoModalidade"));
                modalidade.setNomeModalidade(resultSet.getString("nomeModalidade"));
                licitacao.setModalidade(modalidade);

                return licitacao;
            }
        });
    }
}
