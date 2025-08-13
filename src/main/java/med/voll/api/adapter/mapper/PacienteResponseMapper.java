package med.voll.api.adapter.mapper;

import med.voll.api.adapter.out.persistence.entity.PacienteEntity;
import med.voll.api.domain.model.response.PacienteResponse;

public class PacienteResponseMapper {

    public static PacienteResponse toPacienteResponse(PacienteEntity pacienteEntity){
        return new PacienteResponse(pacienteEntity);
    }
}
