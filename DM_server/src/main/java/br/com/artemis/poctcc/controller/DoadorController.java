package br.com.artemis.poctcc.controller;

import br.com.artemis.poctcc.controller.dto.doador.DoadorRequest;
import br.com.artemis.poctcc.repository.DoadorRepository;
import br.com.artemis.poctcc.repository.PropostaRepository;
import br.com.artemis.poctcc.repository.UsuarioRepository;
import br.com.artemis.poctcc.repository.model.Doador;
import br.com.artemis.poctcc.repository.model.Proposta;
import br.com.artemis.poctcc.repository.model.Usuario;
import br.com.artemis.poctcc.repository.model.enums.StatusProposta;
import br.com.artemis.poctcc.service.DoadorMaper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/doadores")
public class DoadorController {

    private DoadorMaper doadorMaper;
    private DoadorRepository doadorRepository;
    private PropostaRepository propostaRepository;
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Doador> create(@RequestBody DoadorRequest request) {


        // Converter Request em Doador
        Doador doador = doadorMaper.mapearTabela(request);

        // Enviar para o banco um Doador
        doador = doadorRepository.save(doador);

        // Retonar response de sucesso status 201 e Objeto Doador
        return ResponseEntity.status(201).body(doador);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Doador> buscarId(@PathVariable Long id) {

        //TODO Buscar Doador Na Tabela Com Id No Caso De Não Existir, Criar Response Com 404
        Doador doador = doadorRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Doador Não Encontrado"));
        //TODO Retornar Response Com Doador, Status 200
        return ResponseEntity.status(200).body(doador);
    }


    @GetMapping
    public ResponseEntity<Page<Doador>> buscarTodos(Pageable pageable, @RequestParam(value = "name", required = false) String nome) {

        //TODO Buscar Doadores Da Tabela
        Page<Doador> doadores = doadorRepository
                .findAll(pageable);
        //TODO Retornar Response Com Doadores, Status 200
        return ResponseEntity.status(200).body(doadores);
    }

    @GetMapping("/{id}/propostas")
    public ResponseEntity<Page<Proposta>> buscarPorDoador(
            @PathVariable Long id,
            @RequestParam(required = false) StatusProposta status,
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(value = "name", required = false) String nome
    ){

        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Doador Não Encontrada"));
        Page<Proposta> propostas ;
        if(status!= null){
            propostas = propostaRepository
                    .findByItem_UsuarioAndStatus(usuario, status, pageable);
        }
        else {
            propostas = null;
        }
        return ResponseEntity.status(200).body(propostas);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Doador> atualizar(@RequestBody DoadorRequest request, @PathVariable Long id){

        //TODO Buscar No Banco Dados Passando O Id
        Doador doador = doadorRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Doador Não Encontrado"));

        // TODO Atualizar Os Dados Do Doador
        doador = doadorMaper.mapearTabela(request, doador);
        doadorRepository.save(doador);


        // TODO Retornar Response Com Doador Atualizado Status 200

        return ResponseEntity.status(200).body(doador);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        //TODO Buscar No Banco Dados Passando O Id
        Doador doador = doadorRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Doador Não Encontrado"));

        // TODO Atualizar Os Dados Do Doador
        doadorRepository.delete(doador);


        // TODO Retornar Response Com Doador Atualizado Status 200

        return ResponseEntity.noContent().build();
    }
}
