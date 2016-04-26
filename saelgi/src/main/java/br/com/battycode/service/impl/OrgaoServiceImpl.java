package br.com.battycode.service.impl;

import br.com.battycode.dao.OrgaoDAO;
import br.com.battycode.dto.Orgao;
import br.com.battycode.service.OrgaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fefedo on 26/04/16.
 */
@Service
public class OrgaoServiceImpl implements OrgaoService {

    @Autowired
    private OrgaoDAO orgaoDAO;

    public List<Orgao> obterOrgaos(){
        return orgaoDAO.findAllOrgao();
    }

    @Override
    public void removerOrgao(Integer codigo) {
        orgaoDAO.removerOrgao(codigo);
    }


}
