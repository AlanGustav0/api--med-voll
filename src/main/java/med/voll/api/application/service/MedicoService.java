package med.voll.api.application.service;

import jakarta.transaction.Transactional;
import med.voll.api.adapter.mapper.MedicoResponseMapper;
import med.voll.api.adapter.out.persistence.entity.MedicoEntity;
import med.voll.api.domain.model.DadosMedico;
import med.voll.api.domain.model.response.MedicoResponse;
import med.voll.api.domain.port.in.MedicoUseCase;
import med.voll.api.domain.port.out.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService implements MedicoUseCase {

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    @Transactional
    public MedicoResponse cadastrarMedico(DadosMedico medico) {
        MedicoEntity medicoEntity = medicoRepository.save(new MedicoEntity(medico));
        return MedicoResponseMapper.toMedicoResponse(medicoEntity);

    }

    @Override
    @Transactional
    public Page<MedicoResponse> listarMedicos(Pageable paginacao) {
        return medicoRepository.findAll(paginacao).map(MedicoResponse::new);

    }
}
