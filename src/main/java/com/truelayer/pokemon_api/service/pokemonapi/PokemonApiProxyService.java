package com.truelayer.pokemon_api.service.pokemonapi;

import com.truelayer.pokemon_api.service.pokemonapi.bean.PokemonBean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class PokemonApiProxyService {

    private static final Logger logger = Logger.getLogger(PokemonApiProxyService.class.getName());
    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon-species/";

    private final RestTemplate template;

    public PokemonApiProxyService(RestTemplate template) {
        this.template = template;
    }

    public PokemonBean getPokemon(String pokemonName) {
        logger.log(Level.FINE, "Getting pokemon {0}", pokemonName);
        return template.getForObject(BASE_URL + pokemonName, PokemonBean.class);
    }
}
