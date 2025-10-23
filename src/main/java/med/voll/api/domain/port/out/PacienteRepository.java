package med.voll.api.domain.port.out;

import med.voll.api.adapter.out.persistence.entity.PacienteEntity;
import med.voll.api.domain.model.response.PacienteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface PacienteRepository {

    boolean findAtivoById(Long idPaciente);

    PacienteEntity cadastrarPaciente(PacienteEntity paciente);

    PacienteEntity obterPacientePorId(Long idPaciente);

    boolean existePacientePorId(Long idPaciente);

    Page<PacienteResponse> listarTodosOsPacientes(Pageable pageable);
}
