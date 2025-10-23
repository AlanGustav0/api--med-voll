package med.voll.api.adapter.out.persistence.repository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.adapter.out.persistence.entity.MedicoEntity;
import med.voll.api.domain.model.Especialidade;
import med.voll.api.domain.model.response.MedicoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoJpaRepository extends JpaRepository<MedicoEntity, Long> {
    Page<MedicoResponse> findAllByAtivoTrue(Pageable paginacao);

    @Query(value = """
            select * from Medicos m
            where
            m.ativo = 1
            and
            m.especialidade = :especialidade
            and
            m.id not in (
                select c.medico_id from Consultas c
                where
                c.data = :data
            )
            order by rand()
            limit 1
            """,nativeQuery = true)
    MedicoEntity escolherMedicoAleatorioLivreNaData(@Param("especialidade") String especialidade, @NotNull @Future @Param("data")LocalDateTime data);

    @Query("""
            select m.ativo
            from Medico m
            where
            m.id = :idMedico
            """)
    boolean findAtivoById(Long idMedico);
}
