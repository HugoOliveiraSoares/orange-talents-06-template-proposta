package br.com.zupacademy.hugo.proposta.controller.dto;

import br.com.zupacademy.hugo.proposta.model.Legibilidade;
import br.com.zupacademy.hugo.proposta.model.Proposta;

public class PropostaDTO {

    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private Float salario;
    private Legibilidade legibilidade;

    public PropostaDTO(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.legibilidade = proposta.getLegibilidade();
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Float getSalario() {
        return salario;
    }

    public Legibilidade getLegibilidade() {
        return legibilidade;
    }
}
