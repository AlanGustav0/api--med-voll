package med.voll.api.domain.validacoes;

import med.voll.api.configuration.exceptions.ValidacaoException;
import med.voll.api.domain.model.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.port.in.ValidacaoUseCase;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarHorarioAntecedencia implements ValidacaoUseCase<DadosAgendamentoConsultaDTO> {
    @Override
    public void validar(DadosAgendamentoConsultaDTO dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();

        var diferencaEmMinutos = Duration.between(agora,dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
