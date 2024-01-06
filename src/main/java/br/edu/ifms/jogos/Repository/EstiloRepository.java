package br.edu.ifms.jogos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifms.jogos.model.Estilo;

@Repository
public interface EstiloRepository extends JpaRepository<Estilo, Long> {
    
}
