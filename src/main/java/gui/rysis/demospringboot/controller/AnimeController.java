package gui.rysis.demospringboot.controller;


import gui.rysis.demospringboot.domain.Anime;
import gui.rysis.demospringboot.repository.AnimeRepository;
import gui.rysis.demospringboot.requests.AnimePostRequestBody;
import gui.rysis.demospringboot.requests.AnimePutRequestBody;
import gui.rysis.demospringboot.service.AnimeService;
import gui.rysis.demospringboot.util.DateUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Log4j2
@RestController
@RequestMapping("/anime")
@AllArgsConstructor
public class AnimeController {
    private final AnimeRepository animeRepository;
    private DateUtil dateUtil;
    private AnimeService animeService;

    @GetMapping
    public ResponseEntity<Page<Anime>> animeList(Pageable pageable) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAll(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.findByIdOrGetThrowBadRequestException(id));
    }

    @GetMapping("/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody) {
        animeService.save(animePostRequestBody);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping()
    public ResponseEntity<Void> replace(@RequestBody @Valid AnimePutRequestBody animePutRequestBody) {
        animeService.replace(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}