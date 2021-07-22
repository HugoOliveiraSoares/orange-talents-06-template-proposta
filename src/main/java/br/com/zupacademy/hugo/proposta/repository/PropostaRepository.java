package br.com.zupacademy.hugo.proposta.repository;

import br.com.zupacademy.hugo.proposta.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {



}
