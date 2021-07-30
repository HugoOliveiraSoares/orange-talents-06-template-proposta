package br.com.zupacademy.hugo.proposta.controller.form;

import br.com.zupacademy.hugo.proposta.model.Emissor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CarteiraFORM {

    @NotNull @NotEmpty @Email
    private String email;
    @NotNull
    private Emissor carteira;

    public CarteiraFORM(String email, Emissor carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public Emissor getCarteira() {
        return carteira;
    }
}
