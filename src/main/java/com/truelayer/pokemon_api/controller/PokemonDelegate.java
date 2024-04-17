package com.truelayer.pokemon_api.controller;

import com.truelayer.pokemon_api.controller.bean.PokemonResponseBean;
import com.truelayer.pokemon_api.controller.bean.PokemonResponseBeanBuilder;
import com.truelayer.pokemon_api.exception.ValidationException;
import com.truelayer.pokemon_api.service.pokemonapi.PokemonApiProxyService;
import com.truelayer.pokemon_api.service.pokemonapi.bean.FlavorTextEntryBean;
import com.truelayer.pokemon_api.service.pokemonapi.bean.PokemonBean;
import com.truelayer.pokemon_api.service.translator.TranslatorProxyService;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.truelayer.pokemon_api.utils.StringUtils.sanitize;

@Component
public class PokemonDelegate {

    private final PokemonApiProxyService pokemonApiProxyService;
    private final TranslatorProxyService translatorProxyService;

    public PokemonDelegate(PokemonApiProxyService pokemonApiProxyService, TranslatorProxyService translatorProxyService) {
        this.pokemonApiProxyService = pokemonApiProxyService;
        this.translatorProxyService = translatorProxyService;
    }

    public PokemonResponseBean getPokemonDetails(String request) {
        String pokemonName = validatePokemonName(request);

        return Optional.of(pokemonName)
                .map(pokemonApiProxyService::getPokemon)
                .map(pokemon -> buildResponse(pokemon, getDescription(pokemon.getFlavorTextEntries())))
                .orElseThrow(() -> new IllegalStateException("Something went wrong while getting pokemon details"));
    }

    public PokemonResponseBean getTranslatedPokemonDetails(String request) {
        String pokemonName = validatePokemonName(request);

        PokemonBean pokemonBean = Optional.of(pokemonName)
                .map(pokemonApiProxyService::getPokemon)
                .orElseThrow(() -> new IllegalStateException("Something went wrong while getting pokemon details"));

        String description = getDescription(pokemonBean.getFlavorTextEntries());

        try {
            description = sanitize(description);
            String translated;
            if (pokemonBean.isLegendary() || "cave".equalsIgnoreCase(getHabitat(pokemonBean))) {
                translated = translatorProxyService.getYodaTranslation(description);
            } else {
                translated = translatorProxyService.getShakespeareTranslation(description);
            }
            return buildResponse(pokemonBean, translated);

        } catch (Exception e) {
            return buildResponse(pokemonBean, description);
        }

    }

    private String validatePokemonName(String name) {
        return Optional.ofNullable(name)
                .filter(StringUtils::isNotBlank)
                .map(String::toLowerCase)
                .orElseThrow(() -> new ValidationException("Pokemon name cannot be empty"));
    }

    protected PokemonResponseBean buildResponse(PokemonBean pokemon, String description) {
        return new PokemonResponseBeanBuilder()
                .withName(pokemon.getName())
                .withHabitat(getHabitat(pokemon))
                .withIsLegendary(pokemon.isLegendary())
                .withDescription(description)
                .build();
    }

    protected String getDescription(List<FlavorTextEntryBean> flavorTextEntries) {
        return flavorTextEntries.stream()
                .filter(entry -> entry.getLanguage().getName().equalsIgnoreCase("en"))
                .findFirst()
                .map(FlavorTextEntryBean::getFlavorText)
                .orElse("");
    }

    private String getHabitat(PokemonBean pokemon) {
        return pokemon.getHabitat().getName();
    }
}
