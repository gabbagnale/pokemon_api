package com.truelayer.pokemon_api.service.pokemonapi;

import com.truelayer.pokemon_api.service.pokemonapi.bean.PokemonBean;
import com.truelayer.pokemon_api.utils.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PokemonApiProxyServiceTest extends BaseTest {

    @Mock private RestTemplate template;

    @InjectMocks private PokemonApiProxyService proxyService;

    @AfterEach
    void verifyAfter() {
        Mockito.verifyNoMoreInteractions(template);
    }

    @Test
    void getPokemon_shouldGet() {
        String pokemonName = random(String.class);
        PokemonBean mockedResponse = random(PokemonBean.class);
        String uri = "https://pokeapi.co/api/v2/pokemon-species/" + pokemonName;
        when(template.getForObject(uri, PokemonBean.class)).thenReturn(mockedResponse);

        PokemonBean result = proxyService.getPokemon(pokemonName);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(mockedResponse);

        verify(template).getForObject(uri, PokemonBean.class);

    }
}
