package br.com.battycode.jpa.repository;

import br.com.battycode.dto.Edital;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by fefedo on 13/09/16.
 */
public interface EditalRepository extends CrudRepository<Edital, Integer> {

    @Query("select e from Edital e where e.codigoLicitacao = ?1")
    Edital findEditalPorLicitacao(Integer codigoLicitacao);

    @Query("select nmArquivoEdital from Edital e where e.codigoLicitacao = ?1")
    String findNmArquivoEdital(Integer codigoLicitacao);

}
