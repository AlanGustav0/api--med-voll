package med.voll.api.adapter.in;

import jakarta.validation.Valid;
import med.voll.api.domain.model.AtualizarPacienteDTO;
import med.voll.api.domain.model.DadosPacienteDTO;
import med.voll.api.domain.model.response.PacienteResponse;
import med.voll.api.domain.port.in.PacienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    @Autowired
    private PacienteUseCase pacienteUseCase;

    @PostMapping("/cadastrar") ResponseEntity<?> cadastrarPaciente(@RequestBody @Valid DadosPacienteDTO paciente, UriComponentsBuilder uriBuilder){
        try{
            PacienteResponse response = pacienteUseCase.cadastrarPaciente(paciente);
            var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(response.id()).toUri();
            return ResponseEntity.created(uri).body(response);

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

    @PutMapping("atualizar")
    public ResponseEntity<?> atualizarPaciente(@RequestBody @Valid AtualizarPacienteDTO dadosPaciente){
        try{
            var response = pacienteUseCase.atualizarPaciente(dadosPaciente);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar paciente");
        }
    }

    @DeleteMapping("inativar/{id}")
    public ResponseEntity<?> inativarPaciente(@PathVariable Long id){
        try{
            var response = pacienteUseCase.inativarPaciente(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inativar paciente");
        }
    }

    @GetMapping("detalhar/{id}")
    public ResponseEntity<?> detalharPaciente(@PathVariable Long id){

         var response = pacienteUseCase.detalharPaciente(id);
         return ResponseEntity.ok(response);
    }
}
