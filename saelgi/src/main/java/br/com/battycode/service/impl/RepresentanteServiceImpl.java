package br.com.battycode.service.impl;

import br.com.battycode.dto.Representante;
import br.com.battycode.jpa.repository.RepresentanteRepository;
import br.com.battycode.service.RepresentanteService;
import com.google.common.collect.Lists;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public void enviarEmailRepresentanteLicitacao(){
        Email from = new Email("test@example.com");
        String subject = "Hello World from the SendGrid Java Library!";
        Email to = new Email("test@example.com");
        Content content = new Content("text/plain", "Hello, Email!");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);
            System.out.println(response.statusCode);
            System.out.println(response.body);
            System.out.println(response.headers);
        } catch (IOException ex) {
            throw ex;
        }
    }

}
