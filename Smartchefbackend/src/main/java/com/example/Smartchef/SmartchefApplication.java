package com.example.Smartchef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SmartchefApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SmartchefApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            String sql = "SELECT COUNT(*) FROM usuarios";

            Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
            System.out.println("✅ CONEXIÓN EXITOSA A POSTGRESQL.");
            System.out.println("Cantidad de usuarios en la base: " + count);

        } catch (Exception e) {
            System.err.println("⚠️ ADVERTENCIA: No se pudo consultar la tabla 'usuarios'.");
            System.err.println("Posibles causas:");
            System.err.println("1. La tabla aún no existe (Hibernate no la ha creado todavía).");
            System.err.println("2. El nombre de la tabla en Postgres tiene mayúsculas (ej: \"Usuarios\").");
            System.err.println("Error técnico: " + e.getMessage());
        }
    }
}