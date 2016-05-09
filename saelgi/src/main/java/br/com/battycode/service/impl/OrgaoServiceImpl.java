package br.com.battycode.service.impl;

import br.com.battycode.dao.EnderecoDAO;
import br.com.battycode.dao.OrgaoDAO;
import br.com.battycode.dto.Endereco;
import br.com.battycode.dto.Licitacao;
import br.com.battycode.dto.Orgao;
import br.com.battycode.jpa.repository.EnderecoRepository;
import br.com.battycode.jpa.repository.LicitacaoRepository;
import br.com.battycode.jpa.repository.OrgaoRepository;
import br.com.battycode.service.OrgaoService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fefedo on 26/04/16.
 */
@Service
public class OrgaoServiceImpl implements OrgaoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private OrgaoRepository orgaoRepository;


    @Override
    public void removerOrgao(Integer codigo) {
        Orgao orgao = orgaoRepository.findOne(codigo);
        orgao.setFlagExcluido("S");
        orgaoRepository.save(orgao);
    }

    @Override
    public void criarEditarOrgao(Orgao orgao) {
        orgao.setFlagExcluido("N");
        orgao.getEndereco().setFlagExcluido("N");
        orgaoRepository.save(orgao);
    }

    @Override
    public List<Orgao> obterTodosOrgaos(){
        ArrayList<Orgao> orgaoList = Lists.newArrayList(orgaoRepository.findAll());
        for(int i = 0 ; i < orgaoList.size() ; i++){
            if (orgaoList.get(i).getFlagExcluido().equals("S")){
                orgaoList.remove(i);
            }
        }
        return orgaoList;
    }

    @Override
    public Orgao obterOrgao(Integer codigo){
        return orgaoRepository.findOne(codigo);
    }

    @Override
    public void editarEndereco(Endereco endereco) {
        enderecoRepository.save(endereco);
    }

    @Override
    public Endereco obterEndereco(Integer codigo) {
        return enderecoRepository.findOne(codigo);
    }


}
