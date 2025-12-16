
drop table usuarios;
CREATE TABLE usuarios(
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    contrasenia VARCHAR(255) NOT NULL,
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO usuarios (id_usuario, nombre, email, contrasenia)
VALUES (1, 'Usuario Demo', 'demo@smartchef.com', '123456');

ALTER TABLE usuarios
ADD COLUMN vegetariano BOOLEAN DEFAULT FALSE,
ADD COLUMN sin_gluten BOOLEAN DEFAULT FALSE,
ADD COLUMN rapido BOOLEAN DEFAULT FALSE,
ADD COLUMN economico BOOLEAN DEFAULT FALSE;
INSERT INTO usuarios(nombre, email, contrasenia) VALUES('Juan', 'juan@example.com', '123456');


CREATE TABLE recetas(
    id_receta INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    tiempo_preparacion INT,
    vegetariano BOOLEAN DEFAULT FALSE,
    sin_gluten BOOLEAN DEFAULT FALSE,
    rapido BOOLEAN DEFAULT FALSE,
    economico BOOLEAN DEFAULT FALSE,
    foto_url VARCHAR(500),
    CONSTRAINT fk_receta_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
        ON DELETE CASCADE
);

CREATE TABLE ingredientes(
    id_ingredientes INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria ENUM('congelados','bebidas','lacteos','frutas','otros') NOT NULL,
    unidad_medida VARCHAR(20)
);
ALTER TABLE ingredientes
MODIFY categoria ENUM('CONGELADOS','BEBIDAS','LACTEOS','FRUTAS','OTROS') NOT NULL;
CREATE TABLE receta_ingredientes(
    id_receta INT NOT NULL,
    id_ingredientes INT NOT NULL,
    cantidad INT,
    unidad VARCHAR(50),
    PRIMARY KEY (id_receta, id_ingredientes),
    FOREIGN KEY (id_receta) REFERENCES recetas(id_receta) ON DELETE CASCADE,
    FOREIGN KEY (id_ingredientes) REFERENCES ingredientes(id_ingredientes) ON DELETE CASCADE
);


CREATE TABLE instrucciones_receta(
    id_instruccion INT AUTO_INCREMENT PRIMARY KEY,
    id_receta INT NOT NULL,
    paso_numero INT NOT NULL,
    descripcion TEXT NOT NULL,
    imagen_url VARCHAR(255),
    FOREIGN KEY (id_receta) REFERENCES recetas(id_receta) ON DELETE CASCADE
);

CREATE TABLE favoritos(
    id_usuario INT NOT NULL,
    id_receta INT NOT NULL,
    PRIMARY KEY (id_usuario, id_receta),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_receta) REFERENCES recetas(id_receta) ON DELETE CASCADE
);
CREATE TABLE colecciones(
    id_coleccion INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE
);

CREATE TABLE coleccion_recetas(
    id_coleccion INT NOT NULL,
    id_receta INT NOT NULL,
    PRIMARY KEY (id_coleccion, id_receta),
    FOREIGN KEY (id_coleccion) REFERENCES colecciones(id_coleccion) ON DELETE CASCADE,
    FOREIGN KEY (id_receta) REFERENCES recetas(id_receta) ON DELETE CASCADE
);
CREATE TABLE listas_compras(
    id_lista INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    fecha_creacion DATE DEFAULT CURRENT_DATE,
    nombre VARCHAR(100) DEFAULT 'mi lista',
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE
);

CREATE TABLE lista_ingredientes(
    id_lista INT NOT NULL,
    id_ingrediente INT NOT NULL,
    cantidad DECIMAL(10,2),
    comprado BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id_lista, id_ingrediente),
    FOREIGN KEY (id_lista) REFERENCES listas_compras(id_lista) ON DELETE CASCADE,
    FOREIGN KEY (id_ingrediente) REFERENCES ingredientes(id_ingredientes)
);

CREATE TABLE historial(
    id_historial INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_receta INT NOT NULL,
    fecha DATE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_receta) REFERENCES recetas(id_receta) ON DELETE CASCADE
);
CREATE TABLE planificacion(
    id_planificacion INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    fecha DATE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE
);

CREATE TABLE planificacion_comida(
    id_planificacion_comida INT AUTO_INCREMENT PRIMARY KEY,
    id_planificacion INT NOT NULL,
    tipo_comida ENUM('desayuno','almuerzo','cena') NOT NULL,
    id_receta INT NOT NULL,
    FOREIGN KEY (id_planificacion) REFERENCES planificacion(id_planificacion) ON DELETE CASCADE,
    FOREIGN KEY (id_receta) REFERENCES recetas(id_receta) ON DELETE CASCADE
);
