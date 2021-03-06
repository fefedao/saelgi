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
    public List<Licitacao> findAllLicitacao() {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT l.codigo, l.numeroEdital, l.dataDeAbertura, l.dataEntregaDocumentacao, l.dataEntregaProposta, l.codigoModalidade, m.nomeModalidade, l.codigoOrgao, o.nomeOrgao ");
        sb.append("FROM licitacao l ");
        sb.append("JOIN orgao o ON o.codigo = l.codigoOrgao ");
        sb.append("JOIN modalidade m ON m.codigo = l.codigoModalidade ");
        sb.append("WHERE l.flagExcluido = 'N' ");

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

    @Override
    public Licitacao find(Integer codigoLicitacao) {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT l.codigo, l.numeroEdital, l.dataDeAbertura, l.dataEntregaDocumentacao, l.dataEntregaProposta, l.codigoModalidade, m.nomeModalidade, l.codigoOrgao, o.nomeOrgao ");
        sb.append("FROM licitacao l ");
        sb.append("JOIN orgao o ON o.codigo = l.codigoOrgao ");
        sb.append("JOIN modalidade m ON m.codigo = l.codigoModalidade ");
        sb.append("WHERE l.flagExcluido = 'N' ");
        sb.append("AND l.codigo = ?");

        Object[] params = {codigoLicitacao};

        return jdbctemplate.query(sb.toString(), params, new RowMapper<Licitacao>() {
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
        }).stream().findFirst().orElse(null);
    }

    @Override
    public void excluirLicitacao(Integer codigoLicitacao) {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE licitacao SET flagExcluido = 'S' WHERE codigo = " + codigoLicitacao.toString());
        jdbctemplate.execute(sb.toString());
    }

    @Override
    public void criarLicitacao(Licitacao licitacao) {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO licitacao ");
        sb.append("(codigoOrgao, dataDeAbertura, dataEntregaProposta, dataEntregaDocumentacao, codigoModalidade, numeroEdital, flagExcluido) ");
        sb.append(" VALUES ");
        sb.append("(?, ?, ?, ?, ?, ?, 'N')");
        Object[] params = {licitacao.getOrgao().getCodigo(), licitacao.getDataDeAbertura(), licitacao.getDataEntregaProposta(), licitacao.getDataEntregaDocumentacao(), licitacao.getModalidade().getCodigo(), licitacao.getNumeroEdital()};
        jdbctemplate.update(sb.toString(), params);
    }

    @Override
    public void editarLicitacao(Licitacao licitacao) {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE licitacao SET ");
        sb.append("codigoOrgao = ?, ");
        sb.append("dataDeAbertura = ?, ");
        sb.append("dataEntregaProposta = ?, ");
        sb.append("dataEntregaDocumentacao = ?, ");
        sb.append("codigoModalidade = ?, ");
        sb.append("numeroEdital = ? ");
        sb.append("WHERE codigo = " + licitacao.getCodigo());
        Object[] params = {licitacao.getOrgao().getCodigo(), licitacao.getDataDeAbertura(), licitacao.getDataEntregaProposta(), licitacao.getDataEntregaDocumentacao(), licitacao.getModalidade().getCodigo(), licitacao.getNumeroEdital()};
        jdbctemplate.update(sb.toString(), params);
    }

    @Override
    public List<Modalidade> obterModalidades() {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT m.codigo, m.nomeModalidade ");
        sb.append("FROM modalidade m ");
        sb.append("WHERE m.flagExcluido = 'N'");

        return jdbctemplate.query(sb.toString(), new RowMapper<Modalidade>() {
            @Override
            public Modalidade mapRow(ResultSet resultSet, int i) throws SQLException {
                Modalidade modalidade = new Modalidade();
                modalidade.setCodigo(resultSet.getInt("codigo"));
                modalidade.setNomeModalidade(resultSet.getString("nomeModalidade"));
                return modalidade;
            }
        });
    }


}
