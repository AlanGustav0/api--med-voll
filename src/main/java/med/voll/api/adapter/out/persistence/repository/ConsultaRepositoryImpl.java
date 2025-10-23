package med.voll.api.adapter.out.persistence.repository;

import med.voll.api.adapter.out.persistence.entity.ConsultaEntity;
import med.voll.api.domain.port.out.ConsultaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ConsultaRepositoryImpl implements ConsultaRepository {

    private ConsultaJpaRepository consultaJpaRepository;
    @Override
    public boolean existsByMedicoIdAndData(Long aLong, LocalDateTime data) {
        return consultaJpaRepository.existsByMedicoIdAndData(aLong, data);
    }

    @Override
    public boolean existsByPacienteIdAndDataBetween(Long aLong, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario) {
        return consultaJpaRepository.existsByPacienteIdAndDataBetween(aLong,primeiroHorario,ultimoHorario);
    }

    @Override
    public ConsultaEntity agendarConsulta(ConsultaEntity consulta) {
        return consultaJpaRepository.save(consulta);
    }
}
