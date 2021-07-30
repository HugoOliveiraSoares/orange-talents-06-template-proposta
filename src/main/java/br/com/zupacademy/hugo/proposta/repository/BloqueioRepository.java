package br.com.zupacademy.hugo.proposta.repository;

import br.com.zupacademy.hugo.proposta.model.Bloqueado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloqueioRepository extends JpaRepository<Bloqueado, Long> {
}
