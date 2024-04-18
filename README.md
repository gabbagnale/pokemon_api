# Pokemon api
A simple Pokemon Rest API using Java 21.

## External services
This Project uses these public APIs:

https://funtranslations.com/api/shakespeare
https://pokeapi.co/api/v2

## Docs
Once the application is running in local, swagger ui will be available at
```url
localhost:8080/pokemon-api-swagger.html
```

While the doc in open-api at
```url
http://localhost:8080/pokemon-api-docs
```


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

- [GET] /pokemon/translated/{name}

```json
{
  "name": "charizard", 
  "description": "Spits fire that is hot enough to melt boulders. Known to cause forest fires unintentionally", 
  "habitat": "mountain", 
  "isLegendary": false
}
```

### Installation
A shell script run.sh is available to build and run the project.
The script will run the tests, build the image and deploy with docker on 8080 port.

The base url will be 
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
Some improvements can be done for a production environment:
- To avoid too high traffic, a rate limit can be done.
- A cache can be added to make responses faster and to not call every time the external api.
- Code coverage on unit tests and softwares as Sonarqube to inspect the code
- Pipeline to run tests, upload image on a repository and deploy the image on the requested environment.
 