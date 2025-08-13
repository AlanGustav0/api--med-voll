package med.voll.api.domain.port.in;

import med.voll.api.domain.model.DadosPaciente;
import med.voll.api.domain.model.response.PacienteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PacienteUseCase {
    PacienteResponse cadastrarPaciente(DadosPaciente paciente);
    Page<PacienteResponse> listarPacientes(Pageable pageable);
}
