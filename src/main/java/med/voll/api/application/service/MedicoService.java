package med.voll.api.application.service;

import jakarta.transaction.Transactional;
import med.voll.api.adapter.out.persistence.entity.MedicoEntity;
import med.voll.api.domain.model.DadosMedico;
import med.voll.api.domain.port.in.MedicoUseCase;
import med.voll.api.domain.port.out.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService implements MedicoUseCase {

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public String CadastrarMedico(DadosMedico medico) {
        var response = medicoRepository.save(new MedicoEntity(medico));

        return response.toString();

    }
}
