package br.com.artemis.poctcc.service;

import br.com.artemis.poctcc.repository.InstituicaoRepository;
import br.com.artemis.poctcc.repository.ItemRepository;
import br.com.artemis.poctcc.repository.PropostaRepository;
import br.com.artemis.poctcc.repository.model.Instituicao;
import br.com.artemis.poctcc.repository.model.Item;
import br.com.artemis.poctcc.repository.model.Proposta;
import br.com.artemis.poctcc.repository.model.enums.StatusProposta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class PropostaService {

    private ItemRepository itemRepository;
    private InstituicaoRepository instituicaoRepository;
    private PropostaRepository propostaRepository;

    public Proposta criarProposta(Long idItem, Long idInstituicao){
        Proposta proposta = new Proposta();
        Item item = itemRepository
                .findById(idItem)
                .orElseThrow(() -> new RuntimeException());

        Instituicao instituicao = instituicaoRepository
                .findById(idInstituicao)
                .orElseThrow(() -> new RuntimeException());

        proposta.setItem(item);
        proposta.setInstituicao(instituicao);


        proposta.setStatus(StatusProposta.PENDENTE);

        return propostaRepository.save(proposta);
    }
}
