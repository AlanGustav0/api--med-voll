package med.voll.api.adapter.in;

import jakarta.validation.Valid;
import med.voll.api.adapter.out.persistence.entity.UsuarioEntity;
import med.voll.api.configuration.security.TokenService;
import med.voll.api.domain.model.DadosAutenticacaoDTO;
import med.voll.api.domain.model.response.DadosTokenJWTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenticacaoDTO dados){
        var autenticationToken = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        var autenticacao = authenticationManager.authenticate(autenticationToken);

        var tokenJWT = tokenService.gerarToken((UsuarioEntity)autenticacao.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWTResponse(tokenJWT));
    }
}
