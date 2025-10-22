package med.voll.api.domain.port.in;

import med.voll.api.domain.model.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.model.response.DetalhamentoConsultaResponse;

public interface ConsultaUseCase {

    DetalhamentoConsultaResponse agendar(DadosAgendamentoConsultaDTO dados);
}
