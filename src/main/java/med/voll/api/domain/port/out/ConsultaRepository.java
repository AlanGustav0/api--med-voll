package med.voll.api.domain.port.out;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.adapter.out.persistence.entity.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository {
    boolean existsByMedicoIdAndData(Long aLong, LocalDateTime data);

    boolean existsByPacienteIdAndDataBetween(Long aLong, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    ConsultaEntity agendarConsulta(ConsultaEntity consulta);
}
