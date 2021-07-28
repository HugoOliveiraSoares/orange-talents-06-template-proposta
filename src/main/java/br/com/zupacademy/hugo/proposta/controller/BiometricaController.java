package br.com.zupacademy.hugo.proposta.controller;

import br.com.zupacademy.hugo.proposta.controller.dto.BiometriaFORM;
import br.com.zupacademy.hugo.proposta.model.Biometria;
import br.com.zupacademy.hugo.proposta.model.Cartao;
import br.com.zupacademy.hugo.proposta.repository.BiometriaRepository;
import br.com.zupacademy.hugo.proposta.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cartao/{id}")
public class BiometricaController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BiometriaRepository biometriaRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<?> novaBiometria(@PathVariable String id, @RequestBody BiometriaFORM biometriaFORM, UriComponentsBuilder builder){

        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if (cartao.isEmpty())
            return ResponseEntity.notFound().build();

        Biometria biometria = biometriaFORM.toModel(cartao.get());

        biometriaRepository.save(biometria);

        URI urlProposta = builder.path("/biometria/{id}").build(biometria.getId());

        return ResponseEntity.created(urlProposta).build();
    }

}
