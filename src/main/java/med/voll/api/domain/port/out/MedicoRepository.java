package med.voll.api.domain.port.out;


import med.voll.api.adapter.out.persistence.entity.MedicoEntity;
import med.voll.api.domain.model.response.MedicoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
    Page<MedicoResponse> findAllByAtivoTrue(Pageable paginacao);
}
