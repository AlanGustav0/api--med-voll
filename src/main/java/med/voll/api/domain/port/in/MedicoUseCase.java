package med.voll.api.domain.port.in;

import med.voll.api.domain.model.DadosMedico;
import med.voll.api.domain.model.response.MedicoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicoUseCase {

    MedicoResponse cadastrarMedico(DadosMedico medico);
    Page<MedicoResponse> listarMedicos(Pageable paginacao);
}
