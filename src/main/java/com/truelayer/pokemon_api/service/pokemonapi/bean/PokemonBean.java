package com.truelayer.pokemon_api.service.pokemonapi.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PokemonBean {
    private String name;
    private List<FlavorTextEntryBean> flavorTextEntries;
    private HabitatBean habitat;
    private boolean isLegendary;

    public PokemonBean() {
    }

    public PokemonBean(String name, List<FlavorTextEntryBean> flavorTextEntries, HabitatBean habitat, boolean isLegendary) {
        this.name = name;
        this.flavorTextEntries = flavorTextEntries;
        this.habitat = habitat;
        this.isLegendary = isLegendary;
    }

    public String getName() {
        return name;
    }

    public List<FlavorTextEntryBean> getFlavorTextEntries() {
        return flavorTextEntries;
    }

    public HabitatBean getHabitat() {
        return habitat;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    @Override
    public String toString() {
        return "PokemonBean{" +
                "name='" + name + '\'' +
                ", flavorTextEntries=" + flavorTextEntries +
                ", habitat=" + habitat +
                ", isLegendary=" + isLegendary +
                '}';
    }
}
