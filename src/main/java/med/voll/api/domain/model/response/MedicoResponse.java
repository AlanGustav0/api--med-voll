package med.voll.api.domain.model.response;

import med.voll.api.adapter.out.persistence.entity.MedicoEntity;
import med.voll.api.domain.model.Especialidade;

public record MedicoResponse(
    Long id,
    String nome,
    String crm,
    String email,
    Especialidade especialidade
){
    public MedicoResponse(MedicoEntity medico) {
        this(medico.getId(),medico.getNome(), medico.getCrm(), medico.getEmail(), medico.getEspecialidade());
    }
}
