package br.com.zupacademy.hugo.proposta.controller.form;

import br.com.zupacademy.hugo.proposta.model.Proposta;
import br.com.zupacademy.hugo.proposta.validator.CPForCNPJ;
import br.com.zupacademy.hugo.proposta.validator.Unique;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaFORM {

    @NotNull @NotEmpty @CPForCNPJ
    private String documento;
    @NotNull @NotEmpty @Email
    private String email;
    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty
    private String endereco;
    @NotNull @Positive
    private BigDecimal salario;

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

    public BigDecimal getSalario() {
        return salario;
    }

    public Proposta toModel() {
        return new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario);
    }
}
