package med.voll.api.adapter.in;

import med.voll.api.domain.model.DadosMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @PostMapping("/cadastrar")
    public String cadastrarMedico(@RequestBody DadosMedico dadosMedico){

        System.out.println("Médico: " + dadosMedico.nome() + " cadastrado com sucesso!");
        return "Médico Cadastrado!";
    }
}
