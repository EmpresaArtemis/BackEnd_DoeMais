package br.com.artemis.poctcc.service;

import br.com.artemis.poctcc.repository.UsuarioRepository;
import br.com.artemis.poctcc.repository.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationManagerService {

    private UsuarioRepository usuarioRepository;

    //Escopo
    public Usuario getUsuarioByToken(String token){
        Long id = Long.parseLong(token);
        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não cadastrado"));

        return usuario;
    }
}
