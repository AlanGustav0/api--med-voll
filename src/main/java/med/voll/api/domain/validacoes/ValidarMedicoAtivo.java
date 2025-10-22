package med.voll.api.domain.validacoes;

import med.voll.api.configuration.exceptions.ValidacaoException;
import med.voll.api.domain.model.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.port.in.ValidacaoUseCase;
import med.voll.api.domain.port.out.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidarMedicoAtivo implements ValidacaoUseCase<DadosAgendamentoConsultaDTO> {

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public void validar(DadosAgendamentoConsultaDTO dados) {
        if(dados.idMedico() == null) return;

        var medicoAtivo = medicoRepository.findAtivoById(dados.idMedico());

        if(!medicoAtivo){
            throw  new ValidacaoException("O médico está inativo");
        }
    }
}
