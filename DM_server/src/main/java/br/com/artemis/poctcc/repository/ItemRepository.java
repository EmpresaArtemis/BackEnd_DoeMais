package br.com.artemis.poctcc.repository;

import br.com.artemis.poctcc.repository.model.Item;
import br.com.artemis.poctcc.repository.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findAllByUsuario(Usuario usuario, Pageable pageable);
}
