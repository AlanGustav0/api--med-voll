package med.voll.api.domain.port.in;

public interface ValidacaoUseCase<T>{
    public void validar(T dados);
}
