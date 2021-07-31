package br.com.zupacademy.hugo.proposta.repository;

import br.com.zupacademy.hugo.proposta.model.Legibilidade;
import br.com.zupacademy.hugo.proposta.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    List<Proposta> findByCartaoIsNullAndLegibilidadeIs(Legibilidade legibilidade);

    List<Proposta> findByEmail(String email);
}
