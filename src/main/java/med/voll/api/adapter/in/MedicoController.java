package med.voll.api.adapter.in;

import jakarta.transaction.Transactional;
import med.voll.api.domain.model.DadosMedico;
import med.voll.api.domain.port.in.MedicoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoUseCase medicoUseCase;

    @PostMapping("/cadastrar")
    @Transactional
    public String cadastrarMedico(@RequestBody DadosMedico dadosMedico){

        String response = medicoUseCase.CadastrarMedico(dadosMedico);

        if(response != null) System.out.printf("Médico %s cadastrado com sucesso!",response);

        return response;
    }
}
