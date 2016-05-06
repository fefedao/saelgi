package br.com.battycode.service.impl;

import br.com.battycode.dao.LicitacaoDAO;
import br.com.battycode.dao.OrgaoDAO;
import br.com.battycode.dto.Licitacao;
import br.com.battycode.dto.Modalidade;
import br.com.battycode.dto.Orgao;
import br.com.battycode.jpa.repository.LicitacaoRepository;
import br.com.battycode.service.LicitacaoService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fefedo on 14/04/16.
 */
@Service
public class LicitacaoServiceImpl implements LicitacaoService{


    private LicitacaoRepository licitacaoRepository;

    @Autowired
    public void setLicitacaoRepository(LicitacaoRepository licitacaoRepository) {
        this.licitacaoRepository = licitacaoRepository;
    }

    @Autowired
    private LicitacaoDAO licitacaoDAO;

    @Autowired
    private OrgaoDAO orgaoDAO;

    @Override
    public List<Licitacao> obterTodasLicitacoes() {
        return licitacaoDAO.findAllLicitacao();
    }

    @Override
    public Licitacao obterLicitacao(Integer codigo) {
        return licitacaoDAO.find(codigo);
    }

    @Override
    public void criarEditarLicitacao(Licitacao licitacao) {
        if (licitacao.getCodigo() == null){
            licitacaoDAO.criarLicitacao(licitacao);
            return;
        }
        licitacaoDAO.editarLicitacao(licitacao);
    }

    @Override
    public List<Modalidade> obterTodasModalidades() {
        return licitacaoDAO.obterModalidades();
    }

    @Override
    public void removerLicitacao(Integer codigo) {
        licitacaoDAO.excluirLicitacao(codigo);
    }

    @Override
    public List<Orgao> obterOrgaos() {
        return orgaoDAO.findAllOrgaosLicitacao();
    }

    @Override
    public List<Licitacao> obterTodasLicitacoesRepository(){
        return Lists.newArrayList(licitacaoRepository.findAll());
    }

}
