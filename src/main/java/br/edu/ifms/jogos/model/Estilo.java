package br.edu.ifms.jogos.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;


@Entity
public class Estilo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Estilo não cadastrado")
    private String nomeEstilo;

    @OneToMany(mappedBy = "estilo")
    private List<Jogo> jogos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEstilo() {
        return nomeEstilo;
    }

    public void setNomeEstilo(String nomeEstilo) {
        this.nomeEstilo = nomeEstilo;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }
    

}
