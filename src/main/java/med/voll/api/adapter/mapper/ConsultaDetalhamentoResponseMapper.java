package med.voll.api.adapter.mapper;

import med.voll.api.adapter.out.persistence.entity.ConsultaEntity;
import med.voll.api.domain.model.response.DetalhamentoConsultaResponse;

public class ConsultaDetalhamentoResponseMapper {

    public static DetalhamentoConsultaResponse toConsultaResponse(ConsultaEntity consulta){
        return new DetalhamentoConsultaResponse(consulta);
    }
}
