package br.com.zupacademy.hugo.proposta.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime bloqueadoEm = LocalDateTime.now();
    private String ipCliente;
    private String userAgent;
    @OneToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueado() {
    }

    public Bloqueado(String ipCliente, String userAgent, Cartao cartao) {
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
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
}
