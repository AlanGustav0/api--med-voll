package med.voll.api.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarPacienteDTO(
        @NotNull
        Long id,
        String nome,
        @Email
        String email,
        String telefone,
        EnderecoDTO endereco

) {
}
