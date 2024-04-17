package com.truelayer.pokemon_api.service.pokemonapi.bean;

public final class LanguageBeanBuilder {
    private String name;


    public LanguageBeanBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public LanguageBean build() {
        return new LanguageBean(name);
    }
}
