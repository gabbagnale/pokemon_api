package com.truelayer.pokemon_api.service.pokemonapi.bean;

public final class HabitatBeanBuilder {
    private String name;

    public HabitatBeanBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public HabitatBean build() {
        return new HabitatBean(name);
    }
}
