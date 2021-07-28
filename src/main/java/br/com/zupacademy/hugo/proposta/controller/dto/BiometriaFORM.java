package br.com.zupacademy.hugo.proposta.controller.dto;

import br.com.zupacademy.hugo.proposta.model.Biometria;
import br.com.zupacademy.hugo.proposta.model.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Base64;

public class BiometriaFORM {

    @NotNull @NotEmpty
    private String biometria;

    @Deprecated
    public BiometriaFORM() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BiometriaFORM(String biometria) {
        this.biometria = biometria;
    }

    public String getBiometria() {
        return biometria;
    }

    public Biometria toModel(Cartao cartao) {
        String biometriaBase64 = Base64.getEncoder().encodeToString(this.biometria.getBytes());
        return new Biometria(biometriaBase64, cartao);
    }
}
