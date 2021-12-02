package br.com.artemis.poctcc.controller;

import br.com.artemis.poctcc.controller.dto.campanha.CampanhaRequest;
import br.com.artemis.poctcc.repository.CampanhaRepository;
import br.com.artemis.poctcc.repository.model.Campanha;
import br.com.artemis.poctcc.repository.model.Usuario;
import br.com.artemis.poctcc.service.AuthenticationManagerService;
import br.com.artemis.poctcc.service.CampanhaMaper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/campanhas")
public class CampanhaController {

    private CampanhaMaper campanhaMaper;
    private CampanhaRepository campanhaRepository;
    private AuthenticationManagerService authenticationManagerService;

    @PostMapping
    public ResponseEntity<Campanha> create(@RequestBody CampanhaRequest request, @RequestHeader("Authorization") String token){
        Usuario usuario = authenticationManagerService.getUsuarioByToken(token);

        Campanha campanha = campanhaMaper.mapearTabela(request, usuario);

        campanha = campanhaRepository.save(campanha);

        return ResponseEntity.status(201).body(campanha);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Campanha> buscarId(@PathVariable Long id){
        Campanha campanha = campanhaRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrado"));

        return ResponseEntity.status(200).body(campanha);
    }

    @GetMapping
    public ResponseEntity<Page<Campanha>> buscarTodos(
            @RequestHeader("Authorization") String token,
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(value = "name", required = false) String nome
    ){
        Usuario usuario = authenticationManagerService.getUsuarioByToken(token);

        Page<Campanha> campanhas = campanhaRepository
                .findAllByUsuario(usuario, pageable);

        return ResponseEntity.status(200).body(campanhas);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Campanha> atualizar(@RequestBody CampanhaRequest request, @PathVariable Long id){
        Campanha campanha = campanhaRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrado"));

        campanha = campanhaMaper.mapearTabela(request, campanha);
        campanhaRepository.save(campanha);

        return ResponseEntity.status(200).body(campanha);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Campanha> deletar(@PathVariable Long id) {
        Campanha campanha = campanhaRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrado"));

        campanhaRepository.delete(campanha);

        return ResponseEntity.noContent().build();
    }
}
