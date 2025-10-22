package med.voll.api.domain.validacoes;

import med.voll.api.configuration.exceptions.ValidacaoException;
import med.voll.api.domain.model.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.port.in.ValidacaoUseCase;
import med.voll.api.domain.port.out.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarAgendamentoConsultaPacienteInativo implements ValidacaoUseCase<DadosAgendamentoConsultaDTO> {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void validar(DadosAgendamentoConsultaDTO dados) {

        if(dados.idPaciente() == null){
            return;
        }

        var pacienteAtivo = pacienteRepository.findAtivoById(dados.idPaciente());

        if(!pacienteAtivo){
            throw new ValidacaoException("O paciente está inativo");
        }
    }
}
