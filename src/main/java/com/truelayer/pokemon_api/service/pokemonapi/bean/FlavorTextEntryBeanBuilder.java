package com.truelayer.pokemon_api.service.pokemonapi.bean;

public final class FlavorTextEntryBeanBuilder {
    private LanguageBean language;
    private String flavorText;

    public FlavorTextEntryBeanBuilder withLanguage(LanguageBean language) {
        this.language = language;
        return this;
    }

    public FlavorTextEntryBeanBuilder withFlavorText(String flavorText) {
        this.flavorText = flavorText;
        return this;
    }

    public FlavorTextEntryBean build() {
        return new FlavorTextEntryBean(language, flavorText);
    }
}
