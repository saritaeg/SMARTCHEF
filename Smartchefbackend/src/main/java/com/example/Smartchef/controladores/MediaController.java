package com.example.Smartchef.controladores;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/media")
@CrossOrigin(origins = "*") // Ajusta esto si es necesario para seguridad
public class MediaController {

    private final Path ubicacionAlmacenamiento = Paths.get("uploads");

    public MediaController() {
        try {
            Files.createDirectories(ubicacionAlmacenamiento);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo inicializar la carpeta de uploads", e);
        }
    }

    @PostMapping("/upload")
    public Map<String, String> subirArchivo(@RequestParam("file") MultipartFile file) {
        String nombreArchivo = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        try {
            Files.copy(file.getInputStream(), this.ubicacionAlmacenamiento.resolve(nombreArchivo), StandardCopyOption.REPLACE_EXISTING);
            // Retornamos la URL completa (asumiendo que corres en localhost:8080)
            String url = "http://localhost:8080/api/media/" + nombreArchivo;
            return Map.of("url", url);
        } catch (IOException e) {
            throw new RuntimeException("Fallo al guardar archivo", e);
        }
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> servirArchivo(@PathVariable String filename) {
        try {
            Path file = ubicacionAlmacenamiento.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("No se puede leer el archivo: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}