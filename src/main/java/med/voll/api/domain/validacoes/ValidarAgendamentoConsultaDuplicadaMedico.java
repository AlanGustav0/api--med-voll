package med.voll.api.domain.validacoes;

import med.voll.api.configuration.exceptions.ValidacaoException;
import med.voll.api.domain.model.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.port.in.ValidacaoUseCase;
import med.voll.api.domain.port.out.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarAgendamentoConsultaDuplicadaMedico implements ValidacaoUseCase<DadosAgendamentoConsultaDTO> {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosAgendamentoConsultaDTO dados) {
        var existeConsultaMedico = consultaRepository.existsByMedicoIdAndData(dados.idMedico(),dados.data());
        if(existeConsultaMedico){
            throw  new ValidacaoException("Médico já possui consulta agendada nesse horário");
        }
    }
}
