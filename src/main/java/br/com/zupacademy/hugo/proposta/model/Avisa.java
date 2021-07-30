package br.com.zupacademy.hugo.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Avisa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String destino;
    @Future @Column(nullable = false)
    private LocalDate validoAte;
    private String ipCliente;
    private String userAgent;
    @OneToOne
    private Cartao cartao;
    private LocalDateTime instanteAviso = LocalDateTime.now();

    @Deprecated
    public Avisa() {
    }

    public Avisa(String destino, LocalDate validoAte, String ipCliente, String userAgent, Cartao cartao) {
        this.destino = destino;
        this.validoAte = validoAte;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getInstanteAviso() {
        return instanteAviso;
    }
}
