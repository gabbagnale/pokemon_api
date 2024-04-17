package com.truelayer.pokemon_api.controller.bean;

public final class PokemonResponseBeanBuilder {
    private String name;
    private String description;
    private String habitat;
    private boolean isLegendary;

    public PokemonResponseBeanBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PokemonResponseBeanBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public PokemonResponseBeanBuilder withHabitat(String habitat) {
        this.habitat = habitat;
        return this;
    }

    public PokemonResponseBeanBuilder withIsLegendary(boolean isLegendary) {
        this.isLegendary = isLegendary;
        return this;
    }

    public PokemonResponseBean build() {
        return new PokemonResponseBean(name, description, habitat, isLegendary);
    }
}
