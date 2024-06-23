package gui.rysis.demospringboot.service;

import gui.rysis.demospringboot.domain.Anime;
import gui.rysis.demospringboot.repository.AnimeRepository;
import gui.rysis.demospringboot.requests.AnimePostRequestBody;
import gui.rysis.demospringboot.requests.AnimePutRequestBody;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
@Service
public class AnimeService {
    //Variables
    private final AnimeRepository animeRepository;

    public  List<Anime> listAll(){
        return animeRepository.findAll();
    }


    public Anime findByIdOrGetThrowBadRequestException(Long id){
       return animeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not found"));
    }

    public Anime save (AnimePostRequestBody animePostRequestBody){
        Anime anime = Anime.builder()
                .name(animePostRequestBody.getName())
                .build();
       return animeRepository.save(anime);
    }

    public void delete(long id){
        findByIdOrGetThrowBadRequestException(id);
        animeRepository.deleteById(id);

    }

    public void replace (AnimePutRequestBody animePutRequestBody){
        findByIdOrGetThrowBadRequestException(animePutRequestBody.getId());

        Anime anime = Anime.builder()
                .name(animePutRequestBody.getName())
                .id(animePutRequestBody.getId())
                .build();
        animeRepository.save(anime);
    }
}
