package med.voll.api.adapter.out.persistence.repository;

import med.voll.api.adapter.out.persistence.entity.PacienteEntity;
import med.voll.api.domain.model.response.PacienteResponse;
import med.voll.api.domain.port.out.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PacienteRepositoryImpl implements PacienteRepository {

    private PacienteJpaRepository pacienteJpaRepository;

    @Override
    public boolean findAtivoById(Long idPaciente) {
        return pacienteJpaRepository.findAtivoById(idPaciente);
    }

    @Override
    public PacienteEntity cadastrarPaciente(PacienteEntity paciente) {
        return pacienteJpaRepository.save(paciente);
    }

    @Override
    public PacienteEntity obterPacientePorId(Long idPaciente) {
        return pacienteJpaRepository.getReferenceById(idPaciente);
    }

    @Override
    public boolean existePacientePorId(Long idPaciente) {
        return pacienteJpaRepository.existsById(idPaciente);
    }


    @Override
    public Page<PacienteResponse> listarTodosOsPacientes(Pageable pageable) {
        return pacienteJpaRepository.findAll(pageable).map(PacienteResponse::new);
    }
}
