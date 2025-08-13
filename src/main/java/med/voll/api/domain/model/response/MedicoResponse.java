package med.voll.api.domain.model.response;

import med.voll.api.adapter.out.persistence.entity.MedicoEntity;
import med.voll.api.domain.model.Especialidade;

public record MedicoResponse(
    String nome,
    String crm,
    String email,
    Especialidade especialidade
){
    public MedicoResponse(MedicoEntity medico) {
        this(medico.getNome(), medico.getCrm(), medico.getEmail(), medico.getEspecialidade());
    }
}
