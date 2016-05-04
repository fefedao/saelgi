package br.com.battycode.dao.impl;

import br.com.battycode.dao.OrgaoDAO;
import br.com.battycode.dto.Endereco;
import br.com.battycode.dto.Esfera;
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
        sb.append("SELECT o.codigo, o.nomeOrgao, o.email, o.cnpj, o.codigoEndereco, o.esfera ");
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
                orgao.setEsfera(Esfera.getEsfera(resultSet.getInt("esfera")));

                Endereco endereco = new Endereco();
                endereco.setCodigo(resultSet.getInt("codigoEndereco"));
                orgao.setEndereco(endereco);
                return orgao;
            }
        });
    }

    @Override
    public Orgao obterOrgao(Integer codigo) {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT o.codigo, o.nomeOrgao, o.email, o.cnpj, o.codigoEndereco, o.esfera ");
        sb.append("FROM orgao o ");
        sb.append("WHERE o.flagExcluido = 'N' ");
        sb.append("AND o.codigo = ?");

        Object[] params = {codigo};

        return jdbctemplate.query(sb.toString(), params, new RowMapper<Orgao>() {
            @Override
            public Orgao mapRow(ResultSet resultSet, int i) throws SQLException {
                Orgao orgao = new Orgao();
                orgao.setCodigo(resultSet.getInt("codigo"));
                orgao.setNomeOrgao(resultSet.getString("nomeOrgao"));
                orgao.setEmail(resultSet.getString("email"));
                orgao.setCnpj(resultSet.getString("cnpj"));
                orgao.setEsfera(Esfera.getEsfera(resultSet.getInt("esfera")));
                Endereco endereco = new Endereco();
                endereco.setCodigo(resultSet.getInt("codigoEndereco"));
                orgao.setEndereco(endereco);
                return orgao;
            }
        }).stream().findFirst().orElse(null);
    }


    @Override
    public void removerOrgao(Integer codigo) {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE orgao SET flagExcluido = 'S' WHERE codigo = " + codigo.toString());
        jdbctemplate.execute(sb.toString());
    }

    @Override
    public void criarOrgao(Orgao orgao) {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO orgao ");
        sb.append("(nomeOrgao, email, cnpj, codigoEndereco, esfera, flagExcluido) ");
        sb.append(" VALUES ");
        sb.append("(?, ?, ?, ?, ?, 'N')");
        Object[] params = {orgao.getNomeOrgao(), orgao.getEmail(), orgao.getCnpj(), orgao.getEndereco().getCodigo(), orgao.getEsfera()};
        jdbctemplate. update(sb.toString(), params);
    }

    @Override
    public void editarOrgao(Orgao orgao) {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE orgao SET ");
        sb.append("nomeOrgao = ?, ");
        sb.append("email = ?, ");
        sb.append("cnpj = ?, ");
        sb.append("codigoEndereco = ?, ");
        sb.append("esfera = ? ");
        sb.append("WHERE codigo = " + orgao.getCodigo());
        Object[] params = {orgao.getNomeOrgao(), orgao.getEmail(), orgao.getCnpj(), orgao.getEndereco().getCodigo(), orgao.getEsfera().getCodigo()};
        jdbctemplate.update(sb.toString(), params);
    }
}
