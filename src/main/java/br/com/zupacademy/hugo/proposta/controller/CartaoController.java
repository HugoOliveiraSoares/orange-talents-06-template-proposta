package br.com.zupacademy.hugo.proposta.controller;

import br.com.zupacademy.hugo.proposta.controller.dto.BiometriaFORM;
import br.com.zupacademy.hugo.proposta.model.CarteiraDigital;
import br.com.zupacademy.hugo.proposta.controller.form.AvisoFORM;
import br.com.zupacademy.hugo.proposta.controller.form.BloquearRequest;
import br.com.zupacademy.hugo.proposta.controller.form.CarteiraFORM;
import br.com.zupacademy.hugo.proposta.model.*;
import br.com.zupacademy.hugo.proposta.model.cartao.Carteira;
import br.com.zupacademy.hugo.proposta.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cartao/{id}")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private AvisaRepository avisaRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Transactional
    @PostMapping(value = "/bloqueio",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> bloqueiaCartao(@PathVariable String id, HttpServletRequest request){

        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if (cartao.isEmpty())
            return ResponseEntity.notFound().build();

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8888/api/cartoes/" + cartao.get().getId() + "/bloqueios";
        BloquearRequest bloquearRequest = new BloquearRequest("proposta");

        try {
            String s = restTemplate.postForObject(url, bloquearRequest, String.class);
            Bloqueado bloqueio = new Bloqueado(request.getRemoteAddr(), request.getHeader("User-Agent"), cartao.get());
            bloqueioRepository.save(bloqueio);
            cartao.get().bloqueiaCartao();
            cartaoRepository.save(cartao.get());

            return ResponseEntity.ok(s);
        } catch (HttpClientErrorException | HttpServerErrorException exception){
            return ResponseEntity.unprocessableEntity().body(exception.getResponseBodyAsString());
    }

    }

    @Transactional
    @PostMapping("/biometria")
    public ResponseEntity<?> novaBiometria(@PathVariable String id, @RequestBody BiometriaFORM biometriaFORM, UriComponentsBuilder builder){

        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if (cartao.isEmpty())
            return ResponseEntity.notFound().build();

        Biometria biometria = biometriaFORM.toModel(cartao.get());

        biometriaRepository.save(biometria);

        URI urlProposta = builder.path("/biometria/{id}").build(biometria.getId());

        return ResponseEntity.created(urlProposta).build();
    }

    @PostMapping(value = "/aviso", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> avisaBanco(@PathVariable String id, @RequestBody @Valid AvisoFORM avisoFORM, HttpServletRequest request ){

        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if (cartao.isEmpty())
            return ResponseEntity.notFound().build();

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8888/api/cartoes/" + cartao.get().getId() + "/avisos";

        try{
            restTemplate.postForObject(url, avisoFORM, String.class);

            Avisa avisa = avisoFORM.toModel(request.getRemoteAddr(), request.getHeader("User-Agent"), cartao.get());
            avisaRepository.save(avisa);

            return ResponseEntity.ok().build();
        }catch (HttpClientErrorException | HttpServerErrorException exception) {
            return ResponseEntity.unprocessableEntity().body(exception.getResponseBodyAsString());
        }

    }

    @PostMapping(value = "/carteira", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> carteira(@PathVariable String id, @RequestBody @Valid CarteiraFORM carteiraFORM, UriComponentsBuilder builder){

        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if (cartao.isEmpty())
            return ResponseEntity.notFound().build();

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8888/api/cartoes/" + cartao.get().getId() + "/carteiras";

        try {
            CarteiraDigital carteiraDigital = restTemplate.postForObject(url, carteiraFORM, CarteiraDigital.class);
            assert carteiraDigital != null;

            carteiraDigital.setEmissor(carteiraFORM.getCarteira());
            carteiraDigital.setCartao(cartao.get());
            carteiraRepository.save(carteiraDigital);

            URI urlProposta = builder.path("/cartao/{id}/carteira/{id}").build(id, carteiraDigital.getId());
            return ResponseEntity.created(urlProposta).build();
        }catch (HttpClientErrorException | HttpServerErrorException exception) {
            return ResponseEntity.unprocessableEntity().body(exception.getResponseBodyAsString());
        }

    }
}
