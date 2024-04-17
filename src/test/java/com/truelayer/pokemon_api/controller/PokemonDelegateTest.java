package com.truelayer.pokemon_api.controller;

import com.truelayer.pokemon_api.controller.bean.PokemonResponseBean;
import com.truelayer.pokemon_api.controller.bean.PokemonResponseBeanBuilder;
import com.truelayer.pokemon_api.service.pokemonapi.PokemonApiProxyService;
import com.truelayer.pokemon_api.service.pokemonapi.bean.*;
import com.truelayer.pokemon_api.service.translator.TranslatorProxyService;
import com.truelayer.pokemon_api.utils.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.truelayer.pokemon_api.utils.StringUtils.sanitize;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PokemonDelegateTest extends BaseTest {

    @Mock
    private PokemonApiProxyService pokemonApiProxyService;
    @Mock private TranslatorProxyService translatorProxyService;
    @InjectMocks @Spy PokemonDelegate pokemonDelegate;

    @AfterEach
    void verifyAfter() {
        Mockito.verifyNoMoreInteractions(pokemonApiProxyService, translatorProxyService);
    }

    @Test
    void getPokemonDetails_shouldGetDetails() {
        String request = random(String.class);
        PokemonBean pokeApiResponse = random(PokemonBean.class);
        when(pokemonApiProxyService.getPokemon(any())).thenReturn(pokeApiResponse);

        String description = random(String.class);
        doReturn(description).when(pokemonDelegate).getDescription(any());

        PokemonResponseBean mockedResponse = random(PokemonResponseBean.class);
        doReturn(mockedResponse).when(pokemonDelegate).buildResponse(any(), any());

        PokemonResponseBean result = pokemonDelegate.getPokemonDetails(request);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(mockedResponse);
        verify(pokemonApiProxyService).getPokemon(request.toLowerCase());
        verify(pokemonDelegate).getDescription(pokeApiResponse.getFlavorTextEntries());
        verify(pokemonDelegate).buildResponse(pokeApiResponse, description);
    }

    static Stream<Arguments> getTranslatedPokemonDetails_shouldGetYoda() {
        return Stream.of(
                Arguments.of(random(PokemonBeanBuilder.class)
                        .withIsLegendary(true)
                        .build()),
                Arguments.of(random(PokemonBeanBuilder.class)
                                .withHabitat(random(HabitatBeanBuilder.class)
                                        .withName("cave")
                                        .build())
                        .build())
        );
    }

    @ParameterizedTest
    @MethodSource
    void getTranslatedPokemonDetails_shouldGetYoda(PokemonBean pokeApiResponse) {
        String request = random(String.class);

        when(pokemonApiProxyService.getPokemon(any())).thenReturn(pokeApiResponse);

        String description = random(String.class);
        doReturn(description).when(pokemonDelegate).getDescription(any());

        String translated = random(String.class);
        when(translatorProxyService.getYodaTranslation(any())).thenReturn(translated);
        PokemonResponseBean mockedResponse = random(PokemonResponseBean.class);
        doReturn(mockedResponse).when(pokemonDelegate).buildResponse(any(), any());

        PokemonResponseBean result = pokemonDelegate.getTranslatedPokemonDetails(request);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(mockedResponse);
        verify(pokemonApiProxyService).getPokemon(request.toLowerCase());
        verify(pokemonDelegate).getDescription(pokeApiResponse.getFlavorTextEntries());
        verify(translatorProxyService).getYodaTranslation(sanitize(description));
        verify(pokemonDelegate).buildResponse(pokeApiResponse, translated);
    }

    @Test
    void getTranslatedPokemonDetails_shouldGetShakespeare() {
        String request = random(String.class);

        PokemonBean pokeApiResponse = random(PokemonBeanBuilder.class)
                .withIsLegendary(false)
                .build();
        when(pokemonApiProxyService.getPokemon(any())).thenReturn(pokeApiResponse);

        String description = random(String.class);
        doReturn(description).when(pokemonDelegate).getDescription(any());

        String translated = random(String.class);
        when(translatorProxyService.getShakespeareTranslation(any())).thenReturn(translated);
        PokemonResponseBean mockedResponse = random(PokemonResponseBean.class);
        doReturn(mockedResponse).when(pokemonDelegate).buildResponse(any(), any());

        PokemonResponseBean result = pokemonDelegate.getTranslatedPokemonDetails(request);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(mockedResponse);
        verify(pokemonApiProxyService).getPokemon(request.toLowerCase());
        verify(pokemonDelegate).getDescription(pokeApiResponse.getFlavorTextEntries());
        verify(translatorProxyService).getShakespeareTranslation(sanitize(description));
        verify(pokemonDelegate).buildResponse(pokeApiResponse, translated);
    }

    @Test
    void getTranslatedPokemonDetails_shouldReturnDefault() {
        String request = random(String.class);

        PokemonBean pokeApiResponse = random(PokemonBeanBuilder.class)
                .withIsLegendary(false)
                .build();
        when(pokemonApiProxyService.getPokemon(any())).thenReturn(pokeApiResponse);

        String description = random(String.class);
        doReturn(description).when(pokemonDelegate).getDescription(any());

        doThrow(RuntimeException.class).when(translatorProxyService).getShakespeareTranslation(any());

        PokemonResponseBean mockedResponse = random(PokemonResponseBean.class);
        doReturn(mockedResponse).when(pokemonDelegate).buildResponse(any(), any());

        PokemonResponseBean result = pokemonDelegate.getTranslatedPokemonDetails(request);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(mockedResponse);
        verify(pokemonApiProxyService).getPokemon(request.toLowerCase());
        verify(pokemonDelegate).getDescription(pokeApiResponse.getFlavorTextEntries());
        verify(translatorProxyService).getShakespeareTranslation(sanitize(description));
        verify(pokemonDelegate).buildResponse(pokeApiResponse, description);
    }

    @Test
    void buildResponse_shouldBuild() {
        PokemonBean pokemon = random(PokemonBean.class);
        String description = random(String.class);

        PokemonResponseBean response = pokemonDelegate.buildResponse(pokemon, description);

        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(new PokemonResponseBeanBuilder()
                        .withName(pokemon.getName())
                        .withHabitat(pokemon.getHabitat().getName())
                        .withIsLegendary(pokemon.isLegendary())
                        .withDescription(description)
                        .build());
    }

    @Test
    void getDescription_shouldGetDescription() {
        FlavorTextEntryBean entry = random(FlavorTextEntryBeanBuilder.class)
                .withLanguage(random(LanguageBeanBuilder.class)
                        .withName("EN")
                        .build())
                .build();

        String description = pokemonDelegate.getDescription(List.of(entry, random(FlavorTextEntryBean.class)));

        assertThat(description).isEqualTo(entry.getFlavorText());
    }

    @Test
    void getDescription_shouldGetEmptyDescription() {

        String description = pokemonDelegate.getDescription(List.of(random(FlavorTextEntryBean.class)));

        assertThat(description).isEmpty();
    }

}
