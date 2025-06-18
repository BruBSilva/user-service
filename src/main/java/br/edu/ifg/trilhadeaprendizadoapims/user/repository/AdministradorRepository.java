package br.edu.ifg.trilhadeaprendizadoapims.user.repository;

import br.edu.ifg.trilhadeaprendizadoapims.user.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
}
