package med.voll.api.adapter.out.persistence.repository;

import med.voll.api.adapter.out.persistence.entity.MedicoEntity;
import med.voll.api.domain.model.Especialidade;
import med.voll.api.domain.model.response.MedicoResponse;
import med.voll.api.domain.port.out.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MedicoRepositoryImpl implements MedicoRepository {

    @Autowired
    private MedicoJpaRepository medicoJpaRepository;

    @Override
    public Page<MedicoResponse> findAllByAtivoTrue(Pageable paginacao) {
        return medicoJpaRepository.findAllByAtivoTrue(paginacao);
    }

    @Override
    public MedicoEntity escolherMedicoAleatorioLivreNaData(String especialidade, LocalDateTime data) {
        return medicoJpaRepository.escolherMedicoAleatorioLivreNaData(especialidade,data);
    }

    @Override
    public boolean findAtivoById(Long idMedico) {
        return medicoJpaRepository.findAtivoById(idMedico);
    }

    @Override
    public MedicoEntity cadastrarMedico(MedicoEntity medico) {
        return medicoJpaRepository.save(medico);
    }

    @Override
    public MedicoEntity obterMedicoPorId(Long idMedico) {
        return medicoJpaRepository.getReferenceById(idMedico);
    }

    @Override
    public boolean existeMedicoPorId(Long idMedico) {
        return medicoJpaRepository.existsById(idMedico);
    }
}
