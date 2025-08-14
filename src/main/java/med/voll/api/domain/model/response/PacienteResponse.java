package med.voll.api.domain.model.response;

import med.voll.api.adapter.out.persistence.entity.PacienteEntity;

public record PacienteResponse(Long id, String nome, String email, String telefone) {

    public PacienteResponse(PacienteEntity pacienteEntity){
        this(pacienteEntity.getId(),pacienteEntity.getNome(), pacienteEntity.getEmail(),pacienteEntity.getTelefone());
    }
}
