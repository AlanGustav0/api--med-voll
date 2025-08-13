package med.voll.api.adapter.in;

import jakarta.validation.Valid;
import med.voll.api.domain.model.DadosPaciente;
import med.voll.api.domain.model.response.PacienteResponse;
import med.voll.api.domain.port.in.PacienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    @Autowired
    private PacienteUseCase pacienteUseCase;

    @PostMapping("/cadastrar") ResponseEntity<?> cadastrarPaciente(@RequestBody @Valid DadosPaciente paciente){
        try{
            PacienteResponse response = pacienteUseCase.cadastrarPaciente(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar Paciente");
        }
    }

    @GetMapping("listar")
    public ResponseEntity<?> listarPacientes(@PageableDefault(size=3,sort="nome") Pageable pageable){
        try{

            var response = pacienteUseCase.listarPacientes(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar pacientes");
        }
    }
}
