package med.voll.api.adapter.out.persistence.repository;

import jakarta.validation.constraints.NotNull;
import med.voll.api.adapter.out.persistence.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteJpaRepository extends JpaRepository<PacienteEntity, Long> {

    @Query("""
            select p.ativo
            from Paciente p
            where
            p.id = :idPaciente
            """)
    Boolean findAtivoById(@NotNull Long idPaciente);
}
