CREATE EXTENSION IF NOT EXISTS "pgcrypto";

DROP TABLE IF EXISTS reseña;
DROP TABLE IF EXISTS album_artistas;
DROP TABLE IF EXISTS cancion_artistas;
DROP TABLE IF EXISTS cancion;
DROP TABLE IF EXISTS album;
DROP TABLE IF EXISTS artista;
DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
    id UUID PRIMARY KEY,
    nombre varchar(255) NOT NULL,
    correo varchar(255) NOT NULL UNIQUE,
    foto_url varchar(255),
    password varchar(255) NOT NULL
);

CREATE TABLE artista (
    id UUID PRIMARY KEY,
    nombre varchar(255) NOT NULL,
    fecha_inicio int NOT NULL,
    fecha_fin int,
    nacimiento date,
    bibliografia text,
    foto_url varchar(255)
);

CREATE TABLE album (
    id UUID PRIMARY KEY,
    nombre varchar(255) NOT NULL,
    descripcion text,
    duracion_segundos int NOT NULL,
    calificacion_promedio double precision,
    total_canciones int,
    fecha_salida int,
    portada_url varchar(255)
);

CREATE TABLE cancion (
    id UUID PRIMARY KEY,
    nombre varchar(255) NOT NULL,
    descripcion text,
    duracion_segundos int NOT NULL,
    fecha_salida int,
    calificacion double precision,
    portada_url varchar(255),

    id_album uuid REFERENCES album(id) ON DELETE CASCADE
);

CREATE TABLE cancion_artistas(
    id_cancion uuid REFERENCES cancion(id) ON DELETE CASCADE,
    id_artista uuid REFERENCES artista(id) ON DELETE CASCADE,
    PRIMARY KEY (id_cancion, id_artista)
);

CREATE TABLE album_artistas(
    id_album uuid REFERENCES album(id) ON DELETE CASCADE,
    id_artista uuid REFERENCES artista(id) ON DELETE CASCADE,
    PRIMARY KEY (id_album, id_artista)
);

CREATE TABLE reseña(
    id uuid PRIMARY KEY,
    contenido TEXT NOT NULL,
    calificacion DOUBLE PRECISION NOT NULL,
    fecha_creacion date,

    id_usuario uuid NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    id_cancion uuid REFERENCES cancion(id) ON DELETE CASCADE,
    id_album uuid REFERENCES album(id) ON DELETE CASCADE,

    CONSTRAINT check_solo_uno CHECK (
    (id_album IS NOT NULL AND id_cancion IS NULL) OR
    (id_album IS NULL AND id_cancion IS NOT NULL)
    )
);

select * from cancion;