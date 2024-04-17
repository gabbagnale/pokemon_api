# Pokemon api
A simple Pokemon Rest API using Java 21.

## External services
This Project uses these public APIs:

https://funtranslations.com/api/shakespeare
https://pokeapi.co/api/v2

## Endpoints

- [GET] /pokemon/{name}

```json
{
  "name": "charizard", 
  "description": "Spits fire that is hot enough to melt boulders. Known to cause forest fires unintentionally", 
  "habitat": "mountain", 
  "isLegendary": false
}
```

```plantuml
skinparam shadowing false
actor User as u
participant "Pokedex" as p
participant "Pokeapi" as api
u->p++: [GET] /pokemon/{name}
p->api++: [GET] pokeapi.co/api/v2/pokemon-species/{name}
api->p--: pokemon
alt response code is 200 
    loop text : flavor_text_entries
        alt text.language = en
            p->p: description : text.flavor_text
        end
    end
    p->u: name : response.name \ndescription : description \nhabitat: response.habitat.name \nisLegendary : response.is_legendary
else
    p->u: ERROR [response code]
end
```

- [GET] /pokemon/translated/{name}

```json
{
  "name": "charizard", 
  "description": "Spits fire that is hot enough to melt boulders. Known to cause forest fires unintentionally", 
  "habitat": "mountain", 
  "isLegendary": false
}
```

```plantuml
skinparam shadowing false
actor User as u
participant "Pokedex" as p
participant "Pokeapi" as api
participant "Translation" as t

u->p++: [GET] /pokemon/{name}
p->api++: [GET] pokeapi.co/api/v2/pokemon-species/{name}
api->p--: pokemon
alt response code is 200 
    loop text : flavor_text_entries
        alt text.language = en
            p->p: description : text.flavor_text
        end
    end
    alt response.habitat.name = cave OR response.is_legendary
       p->t++: [GET] api.funtranslations.com/translate/yoda.json?text=description
       t->p--: translated_text
       alt translated_text
        p->p: description : translated_text
       else
         p->p: description : description
       end
     else
       p->t++: [GET] api.funtranslations.com/translate/shakespeare.json?text=description
       t->p--: translated_text
       alt translated_text
        p->p: description : translated_text
       else
         p->p: description : description
       end
    end
    p->u: name : response.name \ndescription : description \nhabitat: response.habitat.name \nisLegendary : response.is_legendary
else
    p->u: ERROR [response code]
end
```

### Installation
A simple shell script run.sh is available to build and run the project.
The script will run the tests and build the image to expose with docker on 8080 port.

The base url will be then 
```url
http://localhost:8080/v1/pokemon
```

To make the shell script work, maven and docker cli are needed.

#### Maven installation
Follow the guide on
```url 
https://maven.apache.org/install.html
```

#### Docker installation
Follow the guide on
```url 
https://docs.docker.com/engine/install/
```

### Production improvements
Some improvements can be done for a production environment.
In order to avoid too high traffic, a rate limit can be done.
A cache can be added in order to make response faster.

For CI/CD, test coverage can be added with a pipeline to run tests, upload image on a repository and deploy the image on the requested environment. 