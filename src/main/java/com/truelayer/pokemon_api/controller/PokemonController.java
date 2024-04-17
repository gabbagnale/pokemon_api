package com.truelayer.pokemon_api.controller;

import com.truelayer.pokemon_api.controller.bean.PokemonResponseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pokemon/")
public class PokemonController {

    private final PokemonDelegate delegate;

    public PokemonController(PokemonDelegate delegate) {
        this.delegate = delegate;
    }

    @GetMapping("{pokemonName}")
    public PokemonResponseBean getPokemonDetails(@PathVariable String pokemonName) {
        return delegate.getPokemonDetails(pokemonName);
    }

    @GetMapping("/translated/{pokemonName}")
    public PokemonResponseBean getTranslatedPokemonDetails(@PathVariable String pokemonName) {
        return delegate.getTranslatedPokemonDetails(pokemonName);
    }

}
