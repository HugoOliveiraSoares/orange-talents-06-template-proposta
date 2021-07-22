package br.com.zupacademy.hugo.proposta.controller;

import br.com.zupacademy.hugo.proposta.controller.form.PropostaFORM;
import br.com.zupacademy.hugo.proposta.model.Proposta;
import br.com.zupacademy.hugo.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/nova-proposta")
public class NovaPropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping
    public ResponseEntity<?> novaProposta(@RequestBody @Valid PropostaFORM propostaFORM, UriComponentsBuilder builder){

        Proposta proposta = propostaFORM.toModel();
        propostaRepository.save(proposta);

        URI urlProposta = builder.path("/propostas/{id}").build(proposta.getId());
        return ResponseEntity.created(urlProposta).build();

    }

}
