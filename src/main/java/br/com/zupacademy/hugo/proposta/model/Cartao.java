package br.com.zupacademy.hugo.proposta.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Cartao {

    @Id
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Bloqueio> bloqueios;
    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Aviso> avisos;
    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Carteira> carteiras;
    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Parcela> parcelas;
    private int limite;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Renegociacao renegociacao;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Vencimento vencimento;
    private Long idProposta;
    @Enumerated(EnumType.STRING)
    private Estado status;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id, LocalDateTime emitidoEm, String titular, Set<Bloqueio> bloqueios, Set<Aviso> avisos,
                  Set<Carteira> carteiras, Set<Parcela> parcelas, int limite, Renegociacao renegociacao,
                  Vencimento vencimento, Long idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public Set<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public Set<Aviso> getAvisos() {
        return avisos;
    }

    public Set<Carteira> getCarteiras() {
        return carteiras;
    }

    public Set<Parcela> getParcelas() {
        return parcelas;
    }

    public int getLimite() {
        return limite;
    }

    public Renegociacao getRenegociacao() {
        return renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public Estado getStatus() {
        return status;
    }

    public void bloqueiaCartao() {
        this.status = Estado.BLOQUEADO;
    }
}
