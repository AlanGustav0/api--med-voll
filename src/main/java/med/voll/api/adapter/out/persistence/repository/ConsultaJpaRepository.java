package med.voll.api.adapter.out.persistence.repository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.adapter.out.persistence.entity.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaJpaRepository extends JpaRepository<ConsultaEntity, Long> {
    boolean existsByMedicoIdAndData(Long aLong, @NotNull @Future LocalDateTime data);

    boolean existsByPacienteIdAndDataBetween(@NotNull Long aLong, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
