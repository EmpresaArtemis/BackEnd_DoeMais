package br.com.artemis.poctcc.repository;

import br.com.artemis.poctcc.repository.model.Doador;
import br.com.artemis.poctcc.repository.model.Instituicao;
import br.com.artemis.poctcc.repository.model.Proposta;
import br.com.artemis.poctcc.repository.model.Usuario;
import br.com.artemis.poctcc.repository.model.enums.StatusProposta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Page<Proposta> findByItem_Usuario(Usuario usuario, Pageable pageable);
    Page<Proposta> findByInstituicao(Instituicao instituicao, Pageable pageable);
    Page<Proposta> findByInstituicaoAndStatus(Instituicao instituicao, StatusProposta status, Pageable pageable);

    Page<Proposta> findByItem_UsuarioAndStatus(Usuario usuario, StatusProposta status, Pageable pageable);

//    List<Proposta> findByDoadorAndStatus(Doador doador, StatusProposta status);
}
