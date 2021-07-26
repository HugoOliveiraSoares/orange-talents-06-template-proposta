package br.com.zupacademy.hugo.proposta.model;

import br.com.zupacademy.hugo.proposta.controller.dto.ResponseProposta;
import br.com.zupacademy.hugo.proposta.controller.form.PropostaConsulta;
import br.com.zupacademy.hugo.proposta.validator.CPForCNPJ;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @NotEmpty @CPForCNPJ
    private String documento;
    @NotNull @NotEmpty @Email
    private String email;
    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty
    private String endereco;
    @NotNull @Positive
    private Float salario;
    private Legibilidade legibilidade;

    @Deprecated
    public Proposta() {
    }

    public Proposta(@Valid String documento, String email, String nome, String endereco, Float salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public void validadaLegibilidade() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9999/api/solicitacao";

        PropostaConsulta consulta = new PropostaConsulta(this);

        try {
            restTemplate.postForObject(url, consulta, ResponseProposta.class);
            this.legibilidade = Legibilidade.ELEGIVEL;

        }catch (HttpClientErrorException exception){
            this.legibilidade = Legibilidade.NAO_ELEGIVEL;
        }

    }

    public Long getId() {
        return id;
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
