package br.com.zupacademy.hugo.proposta.model;

import br.com.zupacademy.hugo.proposta.controller.dto.ResponseProposta;
import br.com.zupacademy.hugo.proposta.controller.form.PropostaConsulta;
import br.com.zupacademy.hugo.proposta.validator.CPForCNPJ;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.*;
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
    @NotNull @NotEmpty
    private String documento;
    @NotNull @NotEmpty @Email
    private String email;
    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty
    private String endereco;
    @NotNull @Positive
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private Legibilidade legibilidade;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    /**
     * @param documento STRING EM TEXTO LIMPO
     **/
    public Proposta(@Valid String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = new BCryptPasswordEncoder().encode(documento);
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public void validaLegibilidade() {

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

    public BigDecimal getSalario() {
        return salario;
    }

    public Legibilidade getLegibilidade() {
        return legibilidade;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
