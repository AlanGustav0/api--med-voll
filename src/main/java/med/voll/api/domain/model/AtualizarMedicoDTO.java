package med.voll.api.domain.model;

import jakarta.validation.constraints.NotNull;

public record AtualizarMedicoDTO(@NotNull Long id, String nome, String telefone, String email, EnderecoDTO endereco) {
}
