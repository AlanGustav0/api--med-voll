package med.voll.api.domain.port.in;

import jakarta.validation.Valid;
import med.voll.api.domain.model.AtualizarPacienteDTO;
import med.voll.api.domain.model.DadosPacienteDTO;
import med.voll.api.domain.model.response.PacienteResponse;
import med.voll.api.domain.model.response.ResultResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PacienteUseCase {
    PacienteResponse cadastrarPaciente(DadosPacienteDTO paciente);
    Page<PacienteResponse> listarPacientes(Pageable pageable);

    ResultResponse<PacienteResponse> atualizarPaciente(AtualizarPacienteDTO dadosPaciente);

    ResultResponse<PacienteResponse> inativarPaciente(Long id);

    ResultResponse<PacienteResponse> detalharPaciente(Long id);
}
