package br.com.battycode.service.impl;

import br.com.battycode.dao.LicitacaoDAO;
import br.com.battycode.dto.Licitacao;
import br.com.battycode.service.LicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fefedo on 14/04/16.
 */
@Service
public class LicitacaoServiceImpl implements LicitacaoService{

    @Autowired
    private LicitacaoDAO licitacaoDAO;

    @Override
    public List<Licitacao> obterTodasLicitacoes() {
        return licitacaoDAO.findAll();
    }

    @Override
    public void removerLicitacao(Integer codigo) {
        licitacaoDAO.excluirLicitacao(codigo);
    }


}
