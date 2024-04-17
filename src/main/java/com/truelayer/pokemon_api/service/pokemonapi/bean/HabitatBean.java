package com.truelayer.pokemon_api.service.pokemonapi.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HabitatBean {
    private String name;

    public HabitatBean() {
    }
    public HabitatBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "HabitatBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
