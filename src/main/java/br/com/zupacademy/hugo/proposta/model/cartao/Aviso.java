package br.com.zupacademy.hugo.proposta.model.cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate validoAte;
    private String destino;

    public Aviso() {
    }

    public Aviso(Long id, LocalDate validoAte, String destino) {
        this.id = id;
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
