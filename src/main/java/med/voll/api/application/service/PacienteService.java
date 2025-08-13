package med.voll.api.application.service;

import jakarta.transaction.Transactional;
import med.voll.api.adapter.mapper.PacienteResponseMapper;
import med.voll.api.adapter.out.persistence.entity.PacienteEntity;
import med.voll.api.domain.model.DadosPaciente;
import med.voll.api.domain.model.response.PacienteResponse;
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
    public PacienteResponse cadastrarPaciente(DadosPaciente paciente) {
        PacienteEntity pacienteEntity = pacienteRepository.save(new PacienteEntity(paciente));
        return PacienteResponseMapper.toPacienteResponse(pacienteEntity);
    }

    @Override
    @Transactional
    public Page<PacienteResponse> listarPacientes(Pageable pageable) {
        return pacienteRepository.findAll(pageable).map(PacienteResponse::new);
    }
}
