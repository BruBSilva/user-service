package br.edu.ifg.trilhadeaprendizadoapims.user.repository;

import br.edu.ifg.trilhadeaprendizadoapims.user.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
