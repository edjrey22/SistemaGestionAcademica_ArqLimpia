-- Paso 1: ejecutar conectado a la base "postgres"
CREATE DATABASE gestion_academica;

-- Paso 2: conectarse a "gestion_academica" y ejecutar lo siguiente
-- (en psql: \c gestion_academica)

-- Tabla de estudiantes
CREATE TABLE IF NOT EXISTS estudiantes (
    id       INTEGER PRIMARY KEY,
    nombre   VARCHAR(120) NOT NULL,
    correo   VARCHAR(150) NOT NULL UNIQUE
);

-- Índice para facilitar búsquedas por nombre
CREATE INDEX IF NOT EXISTS idx_estudiantes_nombre
    ON estudiantes(nombre);

-- Consultar los registros
SELECT id, nombre, correo
FROM estudiantes
ORDER BY id;

CREATE TABLE IF NOT EXISTS cursos (
                                      id        INTEGER PRIMARY KEY,
                                      nombre    VARCHAR(120) NOT NULL,
    creditos  INTEGER NOT NULL CHECK (creditos > 0)
    );
