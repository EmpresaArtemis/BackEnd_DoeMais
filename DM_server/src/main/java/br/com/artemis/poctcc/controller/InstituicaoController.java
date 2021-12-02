package br.com.artemis.poctcc.controller;

import br.com.artemis.poctcc.controller.dto.instituicao.InstituicaoResquest;
import br.com.artemis.poctcc.repository.InstituicaoRepository;
import br.com.artemis.poctcc.repository.PropostaRepository;
import br.com.artemis.poctcc.repository.model.Instituicao;
import br.com.artemis.poctcc.repository.model.Proposta;
import br.com.artemis.poctcc.repository.model.enums.StatusProposta;
import br.com.artemis.poctcc.service.InstituicaoMaper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping(value = "/instituicoes")
public class InstituicaoController {

    private InstituicaoMaper instituicaoMaper;
    private InstituicaoRepository instituicaoRepository;
    private PropostaRepository propostaRepository;

    @PostMapping()
    public ResponseEntity<Instituicao> create(@RequestBody InstituicaoResquest resquest) {

        // Converte O Resquest Em Doador
        Instituicao instituicao = instituicaoMaper.mapearTabela(resquest);

        // Envia O Doador Para O Banco De Dados
        instituicao = instituicaoRepository.save(instituicao);

        // Mostrar A Resposta Do Status 201 E A Instituicao
        return ResponseEntity.status(201).body(instituicao);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Instituicao> buscarId(@PathVariable Long id) {

        // Buscar Pelo Id No Banco De Dados
        Instituicao instituicao = instituicaoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Instituicao não encontrada"));
        return ResponseEntity.status(200).body(instituicao);
    }


    @GetMapping
    public ResponseEntity<Page<Instituicao>> buscarTodos(
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(value = "name", required = false) String nome
    ){

        Page<Instituicao> instituicoes = instituicaoRepository
                .findAll(pageable);

        return ResponseEntity.status(200).body(instituicoes);
    }

    @GetMapping("/{id}/propostas")
    public ResponseEntity<Page<Proposta>> buscarPorOng(
            @PathVariable Long id,
            @RequestParam(required = false)StatusProposta status,
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(value = "name", required = false) String nome
    ){

        Instituicao instituicao = instituicaoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Instituição Não Encontrada"));
        Page<Proposta> propostas ;
        if(status!= null){
            propostas = propostaRepository
                    .findByInstituicaoAndStatus(instituicao, status, pageable);
        }
        else {
            propostas = propostaRepository
                    .findByInstituicao(instituicao, pageable);
        }
        return ResponseEntity.status(200).body(propostas);
    }

    @PostMapping("/{idInstituicao}/propostas/{idProposta}/aceitar")
    public ResponseEntity<Proposta> aceitarProposta(
            @PathVariable Long idInstituicao,
            @PathVariable Long idProposta
    ){
        Proposta proposta = propostaRepository
                .findById(idProposta)
                .orElseThrow(() -> new RuntimeException("Proposta Não Encontrada"));

        if(idInstituicao==proposta.getInstituicao().getId()){
            proposta.setStatus(StatusProposta.ACEITO);
            propostaRepository.save(proposta);
        }

        return ResponseEntity.status(200).body(proposta);
    }

    @PostMapping("/{idInstituicao}/propostas/{idProposta}/recusado")
    public ResponseEntity<Proposta> recusarProposta(
            @PathVariable Long idInstituicao,
            @PathVariable Long idProposta
    ){
        Proposta proposta = propostaRepository
                .findById(idProposta)
                .orElseThrow(() -> new RuntimeException("Proposta Não Encontrada"));

        if(idInstituicao==proposta.getInstituicao().getId()){
            proposta.setStatus(StatusProposta.RECUSADO);
            propostaRepository.save(proposta);
        }

        return ResponseEntity.status(200).body(proposta);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Instituicao> atualizar(@RequestBody InstituicaoResquest request, @PathVariable Long id) {

        // Buscar Pelo Id No Banco De Dados
        Instituicao instituicao = instituicaoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Instituicao não encontrada"));

        instituicao = instituicaoMaper.mapearTabela(request, instituicao);
        instituicaoRepository.save(instituicao);

        return ResponseEntity.status(200).body(instituicao);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Instituicao> deletar(@PathVariable Long id ){
        Instituicao instituicao = instituicaoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Instituicao não encontrada"));

        instituicaoRepository.delete(instituicao);

        return ResponseEntity.noContent().build();
    }
}
