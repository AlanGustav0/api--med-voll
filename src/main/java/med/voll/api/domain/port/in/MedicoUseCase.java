package med.voll.api.domain.port.in;

import med.voll.api.domain.model.AtualizarMedicoDTO;
import med.voll.api.domain.model.DadosMedicoDTO;
import med.voll.api.domain.model.response.MedicoResponse;
import med.voll.api.domain.model.response.ResultResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MedicoUseCase {

    MedicoResponse cadastrarMedico(DadosMedicoDTO medico);
    Page<MedicoResponse> listarMedicos(Pageable paginacao);
    ResultResponse<MedicoResponse> atualizarMedico(AtualizarMedicoDTO dadosMedico);
    ResultResponse<MedicoResponse> inativarMedico(Long id);
}
