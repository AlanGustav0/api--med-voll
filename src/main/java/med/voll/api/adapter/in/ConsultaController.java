package med.voll.api.adapter.in;

import jakarta.validation.Valid;
import med.voll.api.application.service.ConsultaService;
import med.voll.api.domain.model.DadosAgendamentoConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consultas")
@EnableMethodSecurity(securedEnabled = true)
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping("agendar")
    public ResponseEntity<?> agendar(@RequestBody @Valid DadosAgendamentoConsultaDTO dados){
        var response = consultaService.agendar(dados);
        return ResponseEntity.ok(response);
    }
}
