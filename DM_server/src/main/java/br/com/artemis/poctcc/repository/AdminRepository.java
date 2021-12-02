package br.com.artemis.poctcc.repository;

import br.com.artemis.poctcc.repository.model.Admin;
import br.com.artemis.poctcc.repository.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsuario(Usuario usuario);
}


