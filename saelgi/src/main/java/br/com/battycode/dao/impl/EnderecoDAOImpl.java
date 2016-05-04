package br.com.battycode.dao.impl;

import br.com.battycode.dao.EnderecoDAO;
import br.com.battycode.dto.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by fefedo on 03/05/16.
 */
@Repository
public class EnderecoDAOImpl implements EnderecoDAO{

    @Autowired
    private DataSource datasource;

    @Override
    public Endereco find(Integer codigoEndereco) {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT codigo, municipio, uf, bairro, logradouro, numero, complemento, referencia, cep ");
        sb.append("FROM endereco ");
        sb.append("WHERE flagExcluido = 'N' ");
        sb.append("AND codigo = ?");

        Object[] params = {codigoEndereco};

        return jdbctemplate.query(sb.toString(), params, new RowMapper<Endereco>() {
            @Override
            public Endereco mapRow(ResultSet resultSet, int i) throws SQLException {
                Endereco endereco = new Endereco();
                endereco.setCodigo(resultSet.getInt("codigo"));
                endereco.setMunicipio(resultSet.getString("municipio"));
                endereco.setUf(resultSet.getString("uf"));
                endereco.setBairro(resultSet.getString("bairro"));
                endereco.setLogradouro(resultSet.getString("logradouro"));
                endereco.setNumero(resultSet.getString("numero"));
                endereco.setComplemento(resultSet.getString("complemento"));
                endereco.setReferencia(resultSet.getString("referencia"));
                endereco.setCep(resultSet.getString("cep"));
                return endereco;
            }
        }).stream().findFirst().orElse(null);
    }

    @Override
    public void editarEndereco(Endereco endereco) {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE endereco SET ");
        sb.append("municipio = ?, ");
        sb.append("uf = ?, ");
        sb.append("bairro = ?, ");
        sb.append("logradouro = ?, ");
        sb.append("numero = ? ");
        sb.append("complemento = ? ");
        sb.append("referencia = ? ");
        sb.append("cep = ? ");
        sb.append("WHERE codigo = " + endereco.getCodigo());
        Object[] params = {endereco.getMunicipio(), endereco.getUf(), endereco.getBairro(), endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getReferencia(), endereco.getCep()};
        jdbctemplate.update(sb.toString(), params);
    }

    @Override
    public void criarEndereco(Endereco endereco) {
        JdbcTemplate jdbctemplate = new JdbcTemplate(datasource);
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO endereco ");
        sb.append("(municipio, uf, bairro, logradouro, numero, complemento, referencia, cep, flagExcluido) ");
        sb.append(" VALUES ");
        sb.append("(?, ?, ?, ?, ?, ?, ?, ?, 'N')");
        Object[] params = {endereco.getMunicipio(), endereco.getUf(), endereco.getBairro(), endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getReferencia(), endereco.getCep()};
        jdbctemplate.update(sb.toString(), params);
    }
}
