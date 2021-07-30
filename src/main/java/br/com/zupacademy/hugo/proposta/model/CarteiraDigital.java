package br.com.zupacademy.hugo.proposta.model;

import javax.persistence.*;

@Entity
public class CarteiraDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uuid;
    private String resultado;
    private String id;
    @Enumerated(EnumType.STRING)
    private Emissor emissor;
    @OneToOne
    private Cartao cartao;

    @Deprecated
    public CarteiraDigital() {
    }

    public CarteiraDigital(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public Long getUuid() {
        return uuid;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }

    public Emissor getEmissor() {
        return emissor;
    }

    public void setEmissor(Emissor emissor) {
        this.emissor = emissor;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
