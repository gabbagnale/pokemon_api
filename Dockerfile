FROM eclipse-temurin:21
ADD target/pokemon_api-1.0.0.jar pokemon_api-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pokemon_api-1.0.0.jar"]