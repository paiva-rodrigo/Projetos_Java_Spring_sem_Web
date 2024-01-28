package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.enums.Categoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    @Query("SELECT s FROM Serie s s.series where s.total_temporadas<= :totalTemporadas and s.avaliacao>= :avaliacao;")
    List<Serie> seriesPorTemporadaEAvaliacao(int totalTemporadas, double avaliacao);

    @Query("SELECT s FROM Serie s JOIN s.episodios e where e.titulo ILIKE %:nomeEpisodio%")
    List<Episodio> episodiosPorTrecho(String nomeEpisodio);
}
