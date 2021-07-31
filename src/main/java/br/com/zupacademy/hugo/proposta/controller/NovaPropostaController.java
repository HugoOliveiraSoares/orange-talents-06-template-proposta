package br.com.zupacademy.hugo.proposta.controller;

import br.com.zupacademy.hugo.proposta.controller.dto.PropostaDTO;
import br.com.zupacademy.hugo.proposta.controller.form.PropostaConsulta;
import br.com.zupacademy.hugo.proposta.controller.form.PropostaFORM;
import br.com.zupacademy.hugo.proposta.model.Cartao;
import br.com.zupacademy.hugo.proposta.model.Legibilidade;
import br.com.zupacademy.hugo.proposta.model.Proposta;
import br.com.zupacademy.hugo.proposta.repository.PropostaRepository;
import br.com.zupacademy.hugo.proposta.validator.UniqueDocumentoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proposta")
public class NovaPropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private UniqueDocumentoValidator uniqueDocumentoValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(uniqueDocumentoValidator);
    }

    @PostMapping
    public ResponseEntity<?> novaProposta(@RequestBody @Valid PropostaFORM propostaFORM, UriComponentsBuilder builder){

        Proposta proposta = propostaFORM.toModel();
        propostaRepository.save(proposta);

        proposta.validaLegibilidade();
        propostaRepository.save(proposta);

        URI urlProposta = builder.path("/propostas/{id}").build(proposta.getId());
        return ResponseEntity.created(urlProposta).build();

    }

    @GetMapping("{id}")
    public ResponseEntity<?> consultaProposta(@PathVariable Long id){

        Optional<Proposta> proposta = propostaRepository.findById(id);
        if (proposta.isEmpty())
            return ResponseEntity.notFound().build();

        PropostaDTO propostaDTO = new PropostaDTO(proposta.get());

        return ResponseEntity.ok(propostaDTO);
    }

    @Transactional
    @Scheduled(initialDelay = 5000, fixedDelay = 60000)
    public void associaCartao() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8888/api/cartoes?idProposta=";

        List<Proposta> propostas = propostaRepository.findByCartaoIsNullAndLegibilidadeIs(Legibilidade.ELEGIVEL);
        if (propostas.isEmpty())
            System.out.println("Proposta não encontrada!!!");

        propostas.forEach(proposta -> {
            try {

                Cartao cartao = restTemplate.getForObject(url+proposta.getId(), Cartao.class);

                proposta.setCartao(cartao);
                propostaRepository.save(proposta);
                System.out.println("Funcionou " + proposta.getId());

            }catch (HttpClientErrorException | HttpServerErrorException exception){
                System.out.println( exception.getResponseBodyAsString() );
            }
        });
    }

}
