# ETAPA 1: Compilación (Build)
FROM gradle:8-jdk17 AS build
WORKDIR /app

# Copiamos los archivos de configuración de Gradle primero
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Damos permisos de ejecución al wrapper y descargamos dependencias
RUN chmod +x ./gradlew
RUN ./gradlew dependencies --no-daemon

# Copiamos el código fuente y compilamos el JAR
COPY src src
RUN ./gradlew bootJar -x test --no-daemon

# ETAPA 2: Ejecución (Run)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiamos el JAR generado en la etapa anterior
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]