package br.com.zupacademy.hugo.proposta.model.cartao;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Carteira {

    @Id
    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }
}
