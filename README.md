# Configuración del entorno -- falta terminarlo bien

## **Red de docker**

```bash
docker network create musica
```

Lo que hace es crear una conexión puente entre los contenedores de docker, para que no pongamos la ip, si no el nombre del contenedor

## **Para el contenedor de postgres sera así**

```bash
docker run -d --name MusicaBD --network musica -e POSTGRES_PASSWORD=musica123 -e POSTGRES_DB=musica -p 5432:5432 -v/Users/hiram/proyectos/BackendMusica/datos:/var/lib/postgresql/data postgres:15
```

A este comando solo le tendrán que cambiar su dirección jaja

## **Para el contenedor de spring sera así**

```bash
docker run -it --name MusicaSpring --network musica -v /Users/hiram/proyectos/BackendMusica:/app -v /Users/hiram/.m2:/root/.m2 -p 8080:8080 -e DB_URL=jdbc:postgresql://MusicaBD:5432/musica -e DB_USER=postgres -e DB_PASSWORD=musica123 rrojano/spring-boot
```

También tendrán que cambiar a sus direcciones,  y ya debería correr perfectamente el proyecto del backend en el puerto 8080

Tal vez tengan que crear la carpeta datos antes de ejecutarlo.

Estos comandos solo se ejecutan una vez, de ahi lo único que harán es usar **docker run MusicaBD** o **docker run MusicaSpring**

No muevan los puertos de los comandos tampoco, si no funciona cierren lo que este usando ese puerto.