package br.com.zupacademy.hugo.proposta.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String biometria;
    private LocalDateTime localDateTime = LocalDateTime.now();
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Biometria(String biometria, Cartao cartao) {
        this.biometria = biometria;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getBiometria() {
        return biometria;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

}
