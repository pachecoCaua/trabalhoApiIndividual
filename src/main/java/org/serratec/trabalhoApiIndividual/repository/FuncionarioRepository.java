package org.serratec.trabalhoApiIndividual.repository;

import org.serratec.trabalhoApiIndividual.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
