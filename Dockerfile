# Estágio de compilação
FROM gradle:latest AS build

WORKDIR /app

# Copiar o código-fonte
COPY . .

# Compilar
RUN gradle build

# Estágio de execução
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar o arquivo JAR compilado
COPY --from=build /app/build/libs/audsat-seguradora-api.jar ./

EXPOSE 8080
ENV TZ America/Sao_Paulo

ENTRYPOINT ["java","-jar","audsat-seguradora-api.jar"]
