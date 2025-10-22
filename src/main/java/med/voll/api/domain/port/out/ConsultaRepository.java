package med.voll.api.domain.port.out;

import med.voll.api.adapter.out.persistence.entity.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {
}
