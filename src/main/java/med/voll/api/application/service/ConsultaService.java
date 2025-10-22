package med.voll.api.application.service;

import jakarta.transaction.Transactional;
import med.voll.api.adapter.out.persistence.entity.ConsultaEntity;
import med.voll.api.adapter.out.persistence.entity.MedicoEntity;
import med.voll.api.configuration.exceptions.ValidacaoException;
import med.voll.api.domain.model.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.model.response.DetalhamentoConsultaResponse;
import med.voll.api.domain.port.in.ConsultaUseCase;
import med.voll.api.domain.port.out.ConsultaRepository;
import med.voll.api.domain.port.out.MedicoRepository;
import med.voll.api.domain.port.out.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService implements ConsultaUseCase {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    @Transactional
    public DetalhamentoConsultaResponse agendar(DadosAgendamentoConsultaDTO dados) {

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do médico informado não existe");
        }

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        var consulta = new ConsultaEntity(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
        return null;
    }

    private MedicoEntity escolherMedico(DadosAgendamentoConsultaDTO dados) {

        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(),dados.data());
    }
}
