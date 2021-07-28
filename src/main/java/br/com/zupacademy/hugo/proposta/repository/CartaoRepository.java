package br.com.zupacademy.hugo.proposta.repository;

import br.com.zupacademy.hugo.proposta.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, String> {
}
