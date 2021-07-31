package br.com.zupacademy.hugo.proposta.validator;

import br.com.zupacademy.hugo.proposta.controller.form.PropostaFORM;
import br.com.zupacademy.hugo.proposta.model.Proposta;
import br.com.zupacademy.hugo.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Component
public class UniqueDocumentoValidator implements Validator {

    @Autowired
    private PropostaRepository propostaRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return PropostaFORM.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors())
            return;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        PropostaFORM propostaFORM = (PropostaFORM) o;
        List<Proposta> propostas = propostaRepository.findByEmail(propostaFORM.getEmail());

        propostas.forEach(proposta -> {

            if (encoder.matches(propostaFORM.getDocumento(), proposta.getDocumento())){
                errors.rejectValue("documento", null,"Documento já registrado");
            }
        });

    }
}
