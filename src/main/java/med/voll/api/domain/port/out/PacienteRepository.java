package med.voll.api.domain.port.out;

import jakarta.validation.constraints.NotNull;
import med.voll.api.adapter.out.persistence.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    @Query("""
            select p.ativo
            from Paciente p
            where
            p.id = :idPaciente
            """)
    Boolean findAtivoById(@NotNull Long idPaciente);
}
