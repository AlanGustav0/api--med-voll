package med.voll.api.application.service;

import jakarta.transaction.Transactional;
import med.voll.api.adapter.mapper.MedicoResponseMapper;
import med.voll.api.adapter.mapper.PacienteResponseMapper;
import med.voll.api.adapter.out.persistence.entity.PacienteEntity;
import med.voll.api.domain.model.AtualizarPacienteDTO;
import med.voll.api.domain.model.DadosPacienteDTO;
import med.voll.api.domain.model.response.MedicoResponse;
import med.voll.api.domain.model.response.PacienteResponse;
import med.voll.api.domain.model.response.ResultResponse;
import med.voll.api.domain.port.in.PacienteUseCase;
import med.voll.api.domain.port.out.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService implements PacienteUseCase {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    @Transactional
    public PacienteResponse cadastrarPaciente(DadosPacienteDTO paciente) {
        PacienteEntity pacienteEntity = pacienteRepository.save(new PacienteEntity(paciente));
        return PacienteResponseMapper.toPacienteResponse(pacienteEntity);
    }

    @Override
    @Transactional
    public Page<PacienteResponse> listarPacientes(Pageable pageable) {
        return pacienteRepository.findAll(pageable).map(PacienteResponse::new);
    }

    @Override
    @Transactional
    public ResultResponse<PacienteResponse> atualizarPaciente(AtualizarPacienteDTO dadosPaciente) {
        var paciente = pacienteRepository.getReferenceById(dadosPaciente.id());
        paciente.atualizarInformacoes(dadosPaciente);

        var pacienteAtualizado = PacienteResponseMapper.toPacienteResponse(paciente);

        return new ResultResponse<PacienteResponse>(true, "Paciente Atualizado com sucesso!", pacienteAtualizado);
    }

    @Override
    @Transactional
    public ResultResponse<PacienteResponse> inativarPaciente(Long id) {
        var paciente = pacienteRepository.getReferenceById(id);

        paciente.excluir();
        var pacienteInativo = PacienteResponseMapper.toPacienteResponse(paciente);
        return new ResultResponse<PacienteResponse>(true, "Paciente inativado com sucesso!", pacienteInativo);
    }

    @Override
    public ResultResponse<PacienteResponse> detalharPaciente(Long id) {
        var paciente = pacienteRepository.getReferenceById(id);

        var detalhamentoPaciente = PacienteResponseMapper.toPacienteResponse(paciente);
        return new ResultResponse<PacienteResponse>(true, "Solicitação efetuada com sucesso!", detalhamentoPaciente);
    }
}
