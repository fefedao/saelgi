package br.com.battycode.dao.impl;

import br.com.battycode.dao.OrgaoDAO;
import br.com.battycode.dto.Esfera;
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
 * Created by fefedo on 24/04/16.
 */
@Repository
public class OrgaoDAOImpl implements OrgaoDAO{

    @Autowired
    private DataSource datasource;

    @Override
    public List<Orgao> findAllOrgaosLicitacao() {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT o.codigo, o.nomeOrgao ");
        sb.append("FROM orgao o ");
        sb.append("WHERE o.flagExcluido = 'N' ");

        return jdbctemplate.query(sb.toString(), new RowMapper<Orgao>() {
            @Override
            public Orgao mapRow(ResultSet resultSet, int i) throws SQLException {
                Orgao orgao = new Orgao();
                orgao.setCodigo(resultSet.getInt("codigo"));
                orgao.setNomeOrgao(resultSet.getString("nomeOrgao"));
                return orgao;
            }
        });
    }

    @Override
    public List<Orgao> findAllOrgao() {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT o.codigo, o.nomeOrgao, o.email, o.cnpj, o.codigoEndereco ");
        sb.append("FROM orgao o ");
        sb.append("WHERE o.flagExcluido = 'N' ");

        return jdbctemplate.query(sb.toString(), new RowMapper<Orgao>() {
            @Override
            public Orgao mapRow(ResultSet resultSet, int i) throws SQLException {
                Orgao orgao = new Orgao();
                orgao.setCodigo(resultSet.getInt("codigo"));
                orgao.setNomeOrgao(resultSet.getString("nomeOrgao"));
                orgao.setEmail(resultSet.getString("email"));
                orgao.setCnpj(resultSet.getString("cnpj"));
                orgao.setCodigoEndereco(resultSet.getInt("codigoEndereco"));
                return orgao;
            }
        });
    }

    @Override
    public void removerOrgao(Integer codigo) {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE orgao SET flagExcluido = 'S' WHERE codigo = " + codigo.toString());
        jdbctemplate.execute(sb.toString());
    }
}
