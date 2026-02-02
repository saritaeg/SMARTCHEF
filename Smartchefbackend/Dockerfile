# Usamos una imagen de Java 17 (que es la que estás usando)
FROM eclipse-temurin:17-jdk-alpine

# Creamos un directorio para la app
WORKDIR /app

# Copiamos el archivo JAR desde la carpeta de Gradle al contenedor
# NOTA: Asegúrate de que el nombre coincida con el que tienes en build/libs
COPY build/libs/*.jar app.jar

# Exponemos el puerto (Render usará el que definas en variables de entorno)
EXPOSE 8080


# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]