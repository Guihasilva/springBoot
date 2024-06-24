package gui.rysis.demospringboot.mapper;


import gui.rysis.demospringboot.domain.Anime;
import gui.rysis.demospringboot.requests.AnimePostRequestBody;
import gui.rysis.demospringboot.requests.AnimePutRequestBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class AnimeMapper {


    private static final ModelMapper mapper = new ModelMapper();

    public Anime toAnime(AnimePostRequestBody animePostRequestBody) {
        return mapper.map(animePostRequestBody, Anime.class);
    }

    public Anime toAnime(AnimePutRequestBody animePutRequestBody) {
        return mapper.map(animePutRequestBody, Anime.class);
    }
}