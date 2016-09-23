package br.com.battycode.service.impl;

import br.com.battycode.dto.Edital;
import br.com.battycode.dto.Licitacao;
import br.com.battycode.dto.Representante;
import br.com.battycode.jpa.repository.EditalRepository;
import br.com.battycode.jpa.repository.RepresentanteRepository;
import br.com.battycode.service.RepresentanteService;
import com.google.common.collect.Lists;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fefedo on 13/09/16.
 */
@Service
public class RepresentanteServiceImpl implements RepresentanteService {

    public static final String SENDGRID_API_KEY = "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";
    @Autowired
    private RepresentanteRepository representanteRepository;

    @Autowired
    private EditalRepository editalRepository;

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

    @Override
    public void enviarEmailRepresentanteLicitacao(Licitacao licitacao) throws IOException {
        Email from = new Email("saelgi@saelgi.com.br");
        String subject = licitacao.getNumeroEdital() + " " + licitacao.getOrgao().getNomeOrgao();
        Email to = new Email(licitacao.getRepresentante().getEmail());
        Content content = new Content("text/plain", "Participar da licitacao na data: " + licitacao.getDataDeAberturaText() + " no orgao: " + licitacao.getOrgao().getNomeOrgao());
        Mail mail = new Mail(from, subject, to, content);
        Attachments attachments = new Attachments();
        attachments.setType("application/pdf");
        attachments.setContentId("1");
        Edital editalPorLicitacao = editalRepository.findEditalPorLicitacao(licitacao.getCodigo());
        attachments.setContent(Base64Utils.encodeToString(editalPorLicitacao.getBlEdital()));
        attachments.setFilename(editalPorLicitacao.getNmArquivoEdital());
        mail.addAttachments(attachments);

        SendGrid sg = new SendGrid(SENDGRID_API_KEY);
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
