package med.voll.api.adapter.in;

import med.voll.api.domain.model.DadosPaciente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    @PostMapping("/cadastrar") String cadastrarPaciente(@RequestBody DadosPaciente paciente){
        System.out.printf("Paciente %s cadastrado com sucesso!",paciente.nome());
        return "Paciente cadastrado com sucesso!";
    }
}
