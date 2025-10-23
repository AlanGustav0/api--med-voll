package med.voll.api.domain.port.out;


import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository {
    UserDetails findByLogin(String username);
}
