package com.truelayer.pokemon_api.service.pokemonapi.bean;

import java.util.List;

public final class PokemonBeanBuilder {
    private String name;
    private List<FlavorTextEntryBean> flavorTextEntries;
    private HabitatBean habitat;
    private boolean isLegendary;

    public PokemonBeanBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PokemonBeanBuilder withFlavorTextEntries(List<FlavorTextEntryBean> flavorTextEntries) {
        this.flavorTextEntries = flavorTextEntries;
        return this;
    }

    public PokemonBeanBuilder withHabitat(HabitatBean habitat) {
        this.habitat = habitat;
        return this;
    }

    public PokemonBeanBuilder withIsLegendary(boolean isLegendary) {
        this.isLegendary = isLegendary;
        return this;
    }

    public PokemonBean build() {
        return new PokemonBean(name, flavorTextEntries, habitat, isLegendary);
    }
}
