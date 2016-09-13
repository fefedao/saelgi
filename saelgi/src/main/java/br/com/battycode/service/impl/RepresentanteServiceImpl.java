package br.com.battycode.service.impl;

import br.com.battycode.dto.Representante;
import br.com.battycode.jpa.repository.RepresentanteRepository;
import br.com.battycode.service.RepresentanteService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fefedo on 13/09/16.
 */
@Service
public class RepresentanteServiceImpl implements RepresentanteService {

    @Autowired
    private RepresentanteRepository representanteRepository;

    @Override
    public List<Representante> obterTodosRepresentante() {
        ArrayList<Representante> representanteList = Lists.newArrayList(representanteRepository.findAll());
        for(int i = 0 ; i < representanteList.size() ; i++){
            if (representanteList.get(i).getFlagExcluido().equals("S")){
                representanteList.remove(i);
            }
        }
        return representanteList;
    }

    @Override
    public void removerRepresentante(Integer codigo) {
        Representante representante = representanteRepository.findOne(codigo);
        representante.setFlagExcluido("S");
        representanteRepository.save(representante);
    }

    @Override
    public void criarEditarRepresentante(Representante representante) {
        representante.setFlagExcluido("N");
        representante.getEndereco().setFlagExcluido("N");
        representanteRepository.save(representante);
    }

    @Override
    public Representante obterRepresentante(Integer codigo) {
        return representanteRepository.findOne(codigo);
    }

}
