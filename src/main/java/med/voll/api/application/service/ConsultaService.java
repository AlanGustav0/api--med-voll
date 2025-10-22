package med.voll.api.application.service;

import jakarta.transaction.Transactional;
import med.voll.api.domain.model.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.model.response.DetalhamentoConsultaResponse;
import med.voll.api.domain.port.in.ConsultaUseCase;
import med.voll.api.domain.port.out.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService implements ConsultaUseCase {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    @Transactional
    public DetalhamentoConsultaResponse agendar(DadosAgendamentoConsultaDTO dados) {

        System.out.println(dados);
        return null;
    }
}
