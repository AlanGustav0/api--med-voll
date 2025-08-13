package med.voll.api.domain.port.in;

import med.voll.api.domain.model.DadosMedico;

public interface MedicoUseCase {

    String CadastrarMedico(DadosMedico medico);
}
