package br.com.artemis.poctcc.repository;

import br.com.artemis.poctcc.repository.model.Campanha;
import br.com.artemis.poctcc.repository.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
    Page<Campanha> findAllByUsuario(Usuario usuario, Pageable pageable);
}
