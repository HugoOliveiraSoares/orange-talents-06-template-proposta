package br.com.zupacademy.hugo.proposta.controller.form;

public class BloquearRequest {

    private String sistemaResponsavel;

    @Deprecated
    public BloquearRequest() {
    }

    public BloquearRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
