package gui.rysis.demospringboot.repository;

import gui.rysis.demospringboot.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface AnimeRepository extends JpaRepository<Anime,Long> {

}
