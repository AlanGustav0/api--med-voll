package med.voll.api.adapter.out.persistence.repository;

import med.voll.api.domain.port.out.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private UsuarioJpaRepository usuarioJpaRepository;
    @Override
    public UserDetails findByLogin(String username) {
        return usuarioJpaRepository.findByLogin(username);
    }
}
