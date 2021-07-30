package br.com.zupacademy.hugo.proposta.repository;

import br.com.zupacademy.hugo.proposta.model.Avisa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisaRepository extends JpaRepository<Avisa, Long> {
}
