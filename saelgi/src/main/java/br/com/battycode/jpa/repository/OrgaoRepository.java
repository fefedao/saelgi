package br.com.battycode.jpa.repository;

/**
 * Created by fefedo on 06/05/16.
 */

import br.com.battycode.dto.Orgao;
import org.springframework.data.repository.CrudRepository;

public interface OrgaoRepository extends CrudRepository<Orgao, Integer> {
}
