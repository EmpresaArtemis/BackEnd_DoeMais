package br.com.artemis.poctcc.controller;

import br.com.artemis.poctcc.controller.dto.proposta.PropostaRequest;
import br.com.artemis.poctcc.repository.InstituicaoRepository;
import br.com.artemis.poctcc.repository.PropostaRepository;
import br.com.artemis.poctcc.repository.UsuarioRepository;
import br.com.artemis.poctcc.repository.model.Item;
import br.com.artemis.poctcc.repository.model.Proposta;
import br.com.artemis.poctcc.repository.model.Usuario;
import br.com.artemis.poctcc.service.PropostaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/propostas")
@AllArgsConstructor
public class PropostaController {

    private PropostaService propostaService;
    private PropostaRepository propostaRepository;
    private UsuarioRepository usuarioRepository;
    private InstituicaoRepository instituicaoRepository;

    @PostMapping
    public ResponseEntity<Proposta> create(@RequestBody PropostaRequest request){
        Proposta proposta = propostaService
                .criarProposta(request.getIdItem(), request.getIdInstituicao());

        return ResponseEntity.status(200).body(proposta);
    }

    @GetMapping
    public ResponseEntity<Page<Proposta>> buscarTodos(
            @RequestHeader("Authorization") String token,
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(value = "name", required = false) String nome
    ){

        Usuario usuario = usuarioRepository.findById(Long.parseLong(token))
                .orElseThrow(() -> new RuntimeException());
        
        Page<Proposta> propostas = propostaRepository
                .findByItem_Usuario(usuario, pageable);

        return ResponseEntity.status(200).body(propostas);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Proposta> deletar(@PathVariable Long id){
        Proposta proposta = propostaRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Proposta não encontrada"));

        propostaRepository.delete(proposta);

        return ResponseEntity.noContent().build();
    }
}
