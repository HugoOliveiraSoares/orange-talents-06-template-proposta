package br.com.zupacademy.hugo.proposta.controller;

import br.com.zupacademy.hugo.proposta.controller.dto.PropostaDTO;
import br.com.zupacademy.hugo.proposta.controller.form.PropostaConsulta;
import br.com.zupacademy.hugo.proposta.controller.form.PropostaFORM;
import br.com.zupacademy.hugo.proposta.model.Cartao;
import br.com.zupacademy.hugo.proposta.model.Legibilidade;
import br.com.zupacademy.hugo.proposta.model.Proposta;
import br.com.zupacademy.hugo.proposta.repository.PropostaRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/nova-proposta")
public class NovaPropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping
    public ResponseEntity<?> novaProposta(@RequestBody @Valid PropostaFORM propostaFORM, UriComponentsBuilder builder){

        Proposta proposta = propostaFORM.toModel();
        proposta.validaLegibilidade();
        propostaRepository.save(proposta);

        URI urlProposta = builder.path("/propostas/{id}").build(proposta.getId());
        return ResponseEntity.created(urlProposta).build();

    }

    @GetMapping("{id}")
    public ResponseEntity<?> consulta(@PathVariable Long id){

        Proposta proposta = propostaRepository.getById(id);

        PropostaDTO propostaDTO = new PropostaDTO(proposta);

        return ResponseEntity.ok(propostaDTO);
    }

    @Transactional
    @Scheduled(initialDelay = 2000, fixedDelay = 60000)
    public void associaCartao() throws NotFoundException {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8888/api/cartoes";

        List<Proposta> propostas = propostaRepository.findByCartaoIsNullAndLegibilidadeIs(Legibilidade.ELEGIVEL);
        if (propostas.isEmpty())
            throw new NotFoundException("Proposta não encontrada!!!");

        propostas.forEach(proposta -> {
            try {
                PropostaConsulta consulta = new PropostaConsulta(proposta);
                Cartao cartao = restTemplate.postForObject(url, consulta, Cartao.class);

                proposta.setCartao(cartao);
                propostaRepository.save(proposta);
                System.out.println("Funcionou " + proposta.getId());

            }catch (HttpClientErrorException exception){
                System.out.println( exception.getResponseBodyAsString() );
            }
        });
    }

}
