package br.com.battycode.service.impl;

import br.com.battycode.dao.LicitacaoDAO;
import br.com.battycode.dto.Edital;
import br.com.battycode.dto.Licitacao;
import br.com.battycode.dto.Modalidade;
import br.com.battycode.jpa.repository.EditalRepository;
import br.com.battycode.jpa.repository.LicitacaoRepository;
import br.com.battycode.service.LicitacaoService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fefedo on 14/04/16.
 */
@Service
public class LicitacaoServiceImpl implements LicitacaoService{

    @Autowired
    private LicitacaoRepository licitacaoRepository;

    @Autowired
    private EditalRepository editalRepository;

    @Autowired
    private LicitacaoDAO licitacaoDAO;


    @Override
    public void criarEditarLicitacao(Licitacao licitacao) throws IOException{
        licitacao.setFlagExcluido("N");
        licitacaoRepository.save(licitacao);
    }

    @Override
    public List<Modalidade> obterTodasModalidades() {
        return licitacaoDAO.obterModalidades();
    }

    @Override
    public void removerLicitacao(Integer codigo) {
        Licitacao licitacao = licitacaoRepository.findOne(codigo);
        licitacao.setFlagExcluido("S");
        licitacaoRepository.save(licitacao);
    }

    @Override
    public List<Licitacao> obterTodasLicitacoes(){
        ArrayList<Licitacao> licitacaoList = Lists.newArrayList(licitacaoRepository.findAll());
        for (int i = 0 ; i<licitacaoList.size() ; i++){
            if (licitacaoList.get(i).getFlagExcluido().equals("S")){
                licitacaoList.remove(i);
            }
        }
        return licitacaoList;
    }

    @Override
    public Licitacao obterLicitacao(Integer codigo){
        return licitacaoRepository.findOne(codigo);
    }


    @Override
    public void criarEditarEdital(Edital edital) throws IOException{
        Edital editalToSave = editalRepository.findEditalPorLicitacao(edital.getCodigoLicitacao());
        if (editalToSave != null) {
            editalToSave.setBlEdital(edital.getBlEdital());
            editalToSave.setNmArquivoEdital(edital.getNmArquivoEdital());
            editalRepository.save(editalToSave);
            return;
        }
        editalRepository.save(edital);
    }

    @Override
    public Edital obterEdital(Integer codigoLicitacao){
        return editalRepository.findEditalPorLicitacao(codigoLicitacao);
    }

    @Override
    public String obterNmArquivoEdital(Integer codigoLicitacao){
        return editalRepository.findNmArquivoEdital(codigoLicitacao);
    }
}
