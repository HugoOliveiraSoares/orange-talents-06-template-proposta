package br.com.zupacademy.hugo.proposta.controller.dto;

import br.com.zupacademy.hugo.proposta.model.Legibilidade;

public class ResponseProposta {

    private String documento;
    private String nome;
    private Legibilidade resultadoSolicitacao;
    private Long idProposta;

    public ResponseProposta() {
    }

    public ResponseProposta(String documento, String nome, Legibilidade resultadoSolicitacao, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Legibilidade getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
