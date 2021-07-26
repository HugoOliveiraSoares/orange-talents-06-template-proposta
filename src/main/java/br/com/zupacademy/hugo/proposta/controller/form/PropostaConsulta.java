package br.com.zupacademy.hugo.proposta.controller.form;

import br.com.zupacademy.hugo.proposta.model.Proposta;
import br.com.zupacademy.hugo.proposta.validator.CPForCNPJ;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PropostaConsulta {

    @NotNull @NotEmpty @CPForCNPJ
    private String documento;
    @NotNull @NotEmpty
    private String nome;
    @Positive
    private Long idProposta;

    public PropostaConsulta() {
    }

    public PropostaConsulta(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
