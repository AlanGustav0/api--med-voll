package med.voll.api.domain.model.response;

import med.voll.api.adapter.out.persistence.entity.ConsultaEntity;

import java.time.LocalDateTime;

public record DetalhamentoConsultaResponse(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {

    public DetalhamentoConsultaResponse(ConsultaEntity consulta){
        this(consulta.getId(),consulta.getMedico().getId(),consulta.getPaciente().getId(),consulta.getData());
    }
}
