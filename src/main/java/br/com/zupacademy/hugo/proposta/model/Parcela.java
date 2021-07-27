package br.com.zupacademy.hugo.proposta.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Parcela {

    @Id
    private String id;
    private int quantidade;
    private int valor;

    @Deprecated
    public Parcela() {
    }

    public Parcela(String id, int quantidade, int valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
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
}
