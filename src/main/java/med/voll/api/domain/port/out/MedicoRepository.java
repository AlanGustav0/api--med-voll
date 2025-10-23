package med.voll.api.domain.port.out;



import med.voll.api.adapter.out.persistence.entity.MedicoEntity;
import med.voll.api.domain.model.Especialidade;
import med.voll.api.domain.model.response.MedicoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface MedicoRepository {
    Page<MedicoResponse> findAllByAtivoTrue(Pageable paginacao);
    MedicoEntity escolherMedicoAleatorioLivreNaData(String especialidade, LocalDateTime data);
    boolean findAtivoById(Long idMedico);
    MedicoEntity cadastrarMedico(MedicoEntity medico);
    MedicoEntity obterMedicoPorId(Long idMedico);
    boolean existeMedicoPorId(Long idMedico);


}
