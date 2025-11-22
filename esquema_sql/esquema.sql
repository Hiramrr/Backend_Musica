CREATE EXTENSION IF NOT EXISTS "pgcrypto";

create table usuario (
                         id UUID PRIMARY KEY,
                         nombre varchar(255) not null,
                         correo varchar(255) not null unique,
                         foto_url varchar(255),
                         password varchar(255) not null
);

create table artista (
                         id UUID primary key,
                         nombre varchar(255) not null,
                         fecha_inicio int not null,
                         fecha_fin int,
                         nacimiento date,
                         bibiliografia text,
                         foto_url varchar(255)
);

create table album (
                       id UUID primary key,
                       nombre varchar(255) not null,
                       descripcion text,
                       duracion_segundos int not null,
                       calificacion double precision,
                       total_canciones int,
                       fecha_salida int,
                       portada_url varchar(255)
);


create table cancion (
                         id UUID primary key,
                         nombre varchar(255) not null,
                         descripcion text,
                         duracion_segundos int not null,
                         album_id uuid references album(id) on delete cascade,
                         fecha_salida int,
                         calificacion double precision,
                         portada_url varchar(255)
);

create table cancion_artistas(
                                 id_cancion uuid references cancion(id) on delete cascade,
                                 id_artista uuid references artista(id) on delete cascade,

                                 PRIMARY KEY (id_cancion, id_artista)
);

create table album_artistas(
                               id_album uuid references album(id) on delete cascade,
                               id_artista uuid references artista(id) on delete cascade,

                               PRIMARY KEY (id_album, id_artista)
);

create table reseña(
                       id uuid primary key,
                       contenido TEXT not null,
                       calificacion DOUBLE PRECISION not null,
                       fecha_creacion date,
                       id_usuario uuid references usuario(id) on delete cascade not null,
                       id_cancion uuid references cancion(id) on delete cascade,
                       id_album uuid references album(id) on delete cascade
);

--- proteccion de que la reseña solo sea a un album pero no a una cancion y al reves jaja
ALTER TABLE reseña
    ADD CONSTRAINT check_solo_uno
        CHECK (
            (id_album IS NOT NULL AND id_cancion IS NULL) OR
            (id_album IS NULL AND id_cancion IS NOT NULL)
            );