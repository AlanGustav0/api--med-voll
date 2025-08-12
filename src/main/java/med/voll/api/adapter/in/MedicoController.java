package med.voll.api.adapter.in;

import med.voll.api.domain.model.DadosMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cadastro")
public class MedicoController {

    @PostMapping("/medicos")
    public String cadastrarMedico(@RequestBody DadosMedico medico){

        System.out.println("Médico: " + medico.nome() + " cadastrado com sucesso!");
        return "Médico Cadastrado!";
    }
}
