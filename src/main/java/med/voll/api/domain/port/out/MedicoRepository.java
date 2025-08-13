package med.voll.api.domain.port.out;

import med.voll.api.adapter.out.persistence.entity.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
}
