package med.voll.api.domain.validacoes;

import med.voll.api.configuration.exceptions.ValidacaoException;
import med.voll.api.domain.model.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.port.in.ValidacaoUseCase;
import med.voll.api.domain.port.out.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidarPacientePossuiOutraConsultaNoDia implements ValidacaoUseCase<DadosAgendamentoConsultaDTO> {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosAgendamentoConsultaDTO dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);

        var pacientePossuiConsultaNodia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(),primeiroHorario,ultimoHorario);

        if(pacientePossuiConsultaNodia){
            throw  new ValidacaoException("Paciente já possui consulta agendada no dia");
        }
    }
}
