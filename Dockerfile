FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copia o jar gerado para dentro do container
COPY build/libs/trip-planner-api-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta usada pelo Spring Boot
EXPOSE 8080

# Define o comando padrão de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
