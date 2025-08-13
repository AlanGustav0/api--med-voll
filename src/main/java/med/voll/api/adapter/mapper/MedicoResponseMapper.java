package med.voll.api.adapter.mapper;

import med.voll.api.adapter.out.persistence.entity.MedicoEntity;
import med.voll.api.domain.model.response.MedicoResponse;

public class MedicoResponseMapper {

    public static MedicoResponse toMedicoResponse(MedicoEntity medicoEntity){
        return new MedicoResponse(medicoEntity);
    }
}
