package br.com.zupacademy.hugo.proposta.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Renegociacao {

    @Id
    private String id;
    private int quantidade;
    private int valor;
    private LocalDateTime dataDeCriacao;

    @Deprecated
    public Renegociacao() {
    }

    public Renegociacao(String id, int quantidade, int valor, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getValor() {
        return valor;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
