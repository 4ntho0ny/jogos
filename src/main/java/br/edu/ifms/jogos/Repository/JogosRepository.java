package br.edu.ifms.jogos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifms.jogos.model.Jogo;

@Repository
public interface JogosRepository extends JpaRepository<Jogo, Long> {

}
