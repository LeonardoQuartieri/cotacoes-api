package br.com.bb.cotacao.repository;

import br.com.bb.cotacao.model.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotacoesRepository extends JpaRepository<Cotacao, Long> {

}
