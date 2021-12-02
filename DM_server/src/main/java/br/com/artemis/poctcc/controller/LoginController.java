package br.com.artemis.poctcc.controller;

import br.com.artemis.poctcc.controller.dto.ChaveDTO;
import br.com.artemis.poctcc.controller.dto.LoginDTO;
import br.com.artemis.poctcc.controller.dto.LoginResponse;
import br.com.artemis.poctcc.repository.AdminRepository;
import br.com.artemis.poctcc.repository.DoadorRepository;
import br.com.artemis.poctcc.repository.InstituicaoRepository;
import br.com.artemis.poctcc.repository.model.Admin;
import br.com.artemis.poctcc.repository.model.Doador;
import br.com.artemis.poctcc.repository.model.Instituicao;
import br.com.artemis.poctcc.repository.model.Usuario;
import br.com.artemis.poctcc.repository.UsuarioRepository;
import br.com.artemis.poctcc.service.AuthenticationManagerService;
import br.com.artemis.poctcc.service.LoginMaper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.Random;

@RestController
@AllArgsConstructor
public class LoginController {

    UsuarioRepository repository;
    private AuthenticationManagerService authenticationManagerService;
    private DoadorRepository doadorRepository;
    private InstituicaoRepository instituicaoRepository;
    private AdminRepository adminRepository;
    private LoginMaper loginMaper;

    @PostMapping("/login")
    public ChaveDTO login(@RequestBody LoginDTO loginDTO) {


        Usuario usuarioLogin = repository.findByEmailAndSenha(loginDTO.getEmail(), loginDTO.getSenha());

        return new ChaveDTO(usuarioLogin.getId().toString());
    }

    @GetMapping(value = "/usuario")
    public LoginResponse buscarDadosUsuario(@RequestHeader("Authorization") String token){
        //TODO Atravez do token busque usuario
        Usuario usuario = authenticationManagerService.getUsuarioByToken(token);

        //TODO Verificar tipo do usuario
            //TODO Caso usuario doador busque na tabela de doador e mapeie para login response
        if(usuario.getPerfil().equals("DOADOR")){
            Doador doador = doadorRepository
                    .findByUsuario(usuario);

             return loginMaper.mapear(doador);
            //TODO Caso usuario instituicao busque na tabela instituicao e mapeie para login response
        } else if (usuario.getPerfil().equals("INSTITUICAO")) {
            Instituicao instituicao = instituicaoRepository
                    .findByUsuario(usuario);

            return loginMaper.mapear(instituicao);
        } else if(usuario.getPerfil().equals("ADMIN")){
            Admin admin = adminRepository
                    .findByUsuario(usuario);


            return loginMaper.mapear(admin);
        }

    throw new RuntimeException("Usuario não mapeado");
    }
}
