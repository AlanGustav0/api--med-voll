package med.voll.api.domain.model.response;

import java.time.LocalDateTime;

public record DetalhamentoConsultaResponse(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
}
