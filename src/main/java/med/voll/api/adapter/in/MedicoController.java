package med.voll.api.adapter.in;
import jakarta.validation.Valid;
import med.voll.api.domain.model.DadosMedico;
import med.voll.api.domain.model.response.MedicoResponse;
import med.voll.api.domain.port.in.MedicoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoUseCase medicoUseCase;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarMedico(@RequestBody @Valid DadosMedico dadosMedico){

        try{
            MedicoResponse response = medicoUseCase.cadastrarMedico(dadosMedico);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar Médico");
        }

    }

    @GetMapping("listarMedicos")
    public ResponseEntity<?> listarMedicos(@PageableDefault(size=3,page=1,sort="nome") Pageable pageable){
        try{

            var response = medicoUseCase.listarMedicos(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar médicos");
        }
    }
}
