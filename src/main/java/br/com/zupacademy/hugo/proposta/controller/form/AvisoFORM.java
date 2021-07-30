package br.com.zupacademy.hugo.proposta.controller.form;

import br.com.zupacademy.hugo.proposta.model.Avisa;
import br.com.zupacademy.hugo.proposta.model.Cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoFORM {

    @NotNull @NotEmpty
    private String destino;
    @NotNull @Future
    private LocalDate validoAte;

    public AvisoFORM(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public Avisa toModel(String ipCliente, String userAgent, Cartao cartao) {
        return new Avisa(this.destino, this.validoAte, ipCliente, userAgent, cartao);
    }
}
