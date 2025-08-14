package med.voll.api.domain.model.response;

public record ResultResponse<T>(boolean sucesso, String Mensagem, T dados) { }
