# Trip Planner API

API REST para planejamento de viagens construída com Spring Boot. Fornece recursos para gerenciar usuários, viagens, destinos e atividades, com autenticação via JWT e documentação com OpenAPI/Swagger.

## Tecnologias
- Java 17+
- Spring Boot
- Spring Web, Spring Security, Spring Data JPA
- OpenAPI/Swagger
- Gradle
- Docker

## Estrutura do projeto
```
first-spring-app/
  ├─ src/
  │  ├─ main/java/com/mateus/trip_planner_api/
  │  │  ├─ configuration/      
  │  │  ├─ controllers/        
  │  │  ├─ services/           
  │  │  ├─ repository/         
  │  │  ├─ models/             
  │  │  ├─ dto/                
  │  │  └─ exceptions/         
  │  └─ main/resources/
  │     └─ application.properties
  ├─ build.gradle
  ├─ settings.gradle
  ├─ Dockerfile
  ├─ docker-compose.yml
  └─ README.md
```

## Pré-requisitos
- Java 17+ instalado
- Docker e Docker Compose (opcionais)

## Como executar
### 1) Ambiente de desenvolvimento (Gradle)
No Windows PowerShell:
```bash
# na raiz do projeto (first-spring-app/)
.\gradlew.bat bootRun
```
Linux/macOS:
```bash
./gradlew bootRun
```
A API iniciará (por padrão) em `http://localhost:8080`.

### 2) Executar os testes
Windows:
```bash
.\gradlew.bat test
```
Linux/macOS:
```bash
./gradlew test
```

### 3) Gerar JAR e executar
Build do JAR:
```bash
# Windows
.\gradlew.bat clean build
# Linux/macOS
./gradlew clean build
```
Executar o JAR gerado:
```bash
java -jar build/libs/trip-planner-api-0.0.1-SNAPSHOT.jar
```

### 4) Docker
Build da imagem:
```bash
# na raiz do projeto (first-spring-app/)
docker build -t trip-planner-api:latest .
```
Executar com Docker Compose:
```bash
docker compose up -d
```
A API ficará disponível em `http://localhost:8080` (ajuste portas se necessário no `docker-compose.yml`).

## Configuração
As propriedades padrão ficam em `src/main/resources/application.properties`.
Recomenda-se usar perfis separados:
- `application-dev.yml`
- `application-prod.yml`
- `application-test.yml`

Variáveis comuns:
- Porta da aplicação: `server.port=8080`
- Configurações de segurança/JWT (ex.: segredo, validade) — defina via propriedades de segurança ou variáveis de ambiente conforme seu `SecurityConfig`/`JwtService`.

## Autenticação e segurança
- Autenticação baseada em JWT.
- Obtenha um token (ex.: via endpoint de autenticação) e envie em cada requisição protegida:
```
Authorization: Bearer <seu_token_jwt>
```
- Endpoints públicos/privados são configurados em `configuration/SecurityConfig.java` e o filtro em `configuration/JwtAuthFilter.java`.

## Documentação da API
Com OpenAPI configurado (`OpenApiConfig.java`), acesse após iniciar a aplicação:
- Swagger UI: `http://localhost:8080/swagger-ui/index.html` (ou `.../swagger-ui.html`)
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`


## Domínios principais (controllers)
- `AuthController`: autenticação/registro/login
- `UserController`: usuários
- `TripController`: viagens
- `DestinationController`: destinos
- `ActivitiesController`: atividades




