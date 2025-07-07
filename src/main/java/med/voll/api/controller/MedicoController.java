package med.voll.api.controller;

import med.voll.api.entities.DadosMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping
    public String cadastrarMedico(@RequestBody DadosMedico medico){

        System.out.println("Médico: " + medico.nome() + " cadastrado com sucesso!");
        return "Médico Cadastrado!";
    }
}
