package med.voll.api.domain.model.response;

import med.voll.api.adapter.out.persistence.entity.PacienteEntity;

public record PacienteResponse(String nome, String email, String telefone) {

    public PacienteResponse(PacienteEntity pacienteEntity){
        this(pacienteEntity.getNome(), pacienteEntity.getEmail(),pacienteEntity.getTelefone());
    }
}
