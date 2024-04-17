package com.truelayer.pokemon_api.service.pokemonapi.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FlavorTextEntryBean {
    private LanguageBean language;
    private String flavorText;

    public FlavorTextEntryBean() {
    }

    public FlavorTextEntryBean(LanguageBean language, String flavorText) {
        this.language = language;
        this.flavorText = flavorText;
    }

    public LanguageBean getLanguage() {
        return language;
    }

    public String getFlavorText() {
        return flavorText;
    }
}
