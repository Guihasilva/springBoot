package gui.rysis.demospringboot.service;

import gui.rysis.demospringboot.domain.Anime;
import gui.rysis.demospringboot.exception.BadRequestException;
import gui.rysis.demospringboot.mapper.AnimeMapper;
import gui.rysis.demospringboot.repository.AnimeRepository;
import gui.rysis.demospringboot.requests.AnimePostRequestBody;
import gui.rysis.demospringboot.requests.AnimePutRequestBody;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final AnimeMapper modelMapper;
    public Page<Anime> listAll(Pageable pageable){
        return animeRepository.findAll(pageable);
    }


    public Anime findByIdOrGetThrowBadRequestException(Long id){
       return animeRepository.findById(id).orElseThrow(() -> new BadRequestException("Id not found"));
    }

//    public Anime save (AnimePostRequestBody animePostRequestBody){
//        Anime anime = Anime.builder()
//                .name(animePostRequestBody.getName())
//                .build();
//       return animeRepository.save(anime);
//    }

    @Transactional
    public Anime save (AnimePostRequestBody animePostRequestBody){
        Anime anime = modelMapper.toAnime(animePostRequestBody);
        return animeRepository.save(anime);
    }

    public List<Anime> findByName (String name){
        return animeRepository.findByName(name);
    }

    public void delete(long id){
        findByIdOrGetThrowBadRequestException(id);
        animeRepository.deleteById(id);

    }

    public void replace (AnimePutRequestBody animePutRequestBody){
        findByIdOrGetThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = modelMapper.toAnime(animePutRequestBody);
        animeRepository.save(anime);
    }
}
