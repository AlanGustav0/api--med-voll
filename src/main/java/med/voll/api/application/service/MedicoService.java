package med.voll.api.application.service;

import jakarta.transaction.Transactional;
import med.voll.api.adapter.mapper.MedicoResponseMapper;
import med.voll.api.adapter.out.persistence.entity.MedicoEntity;
import med.voll.api.adapter.out.persistence.repository.MedicoRepositoryImpl;
import med.voll.api.domain.model.AtualizarMedicoDTO;
import med.voll.api.domain.model.DadosMedicoDTO;
import med.voll.api.domain.model.response.MedicoResponse;
import med.voll.api.domain.model.response.ResultResponse;
import med.voll.api.domain.port.in.MedicoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoService implements MedicoUseCase {

    @Autowired
    private MedicoRepositoryImpl medicoRepositoryImpl;

    @Override
    @Transactional
    public MedicoResponse cadastrarMedico(DadosMedicoDTO medico) {
        MedicoEntity medicoEntity = medicoRepositoryImpl.cadastrarMedico(new MedicoEntity(medico));
        return MedicoResponseMapper.toMedicoResponse(medicoEntity);

    }

    @Override
    @Transactional
    public Page<MedicoResponse> listarMedicos(Pageable paginacao) {
        return medicoRepositoryImpl.findAllByAtivoTrue(paginacao);

    }

    @Override
    @Transactional
    public ResultResponse<MedicoResponse> atualizarMedico(AtualizarMedicoDTO dadosMedico) {
        var medico = medicoRepositoryImpl.obterMedicoPorId(dadosMedico.id());
        medico.atualizarInformacoes(dadosMedico);

        var medicoAtualizado = MedicoResponseMapper.toMedicoResponse(medico);

        return new ResultResponse<MedicoResponse>(true, "Médico Atualizado com sucesso!", medicoAtualizado);
    }

    @Override
    @Transactional
    public ResultResponse<MedicoResponse> inativarMedico(Long id) {
        var medico = medicoRepositoryImpl.obterMedicoPorId(id);

        medico.excluir();
        var medicoInativo = MedicoResponseMapper.toMedicoResponse(medico);
        return new ResultResponse<MedicoResponse>(true, "Médico inativado com sucesso!", medicoInativo);

    }

    @Override
    public ResultResponse<MedicoResponse> detalharMedico(Long id) {
        var medico = medicoRepositoryImpl.obterMedicoPorId(id);

        var detalhamentoMedico = MedicoResponseMapper.toMedicoResponse(medico);
        return new ResultResponse<MedicoResponse>(true, "Solicitação realizada com sucesso!", detalhamentoMedico);

    }
}
