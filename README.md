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

**Nota:** Es importante que cambies las rutas de ejemplo por tus rutas reales

## **Para el contenedor de spring sera así**

```bash
docker run -it --name MusicaSpring --network musica -v /Users/hiram/proyectos/BackendMusica:/app -v /Users/hiram/.m2:/root/.m2 -p 8080:8080 -e DB_URL=jdbc:postgresql://MusicaBD:5432/musica -e DB_USER=postgres -e DB_PASSWORD=musica123 rrojano/spring-boot
```

**Nota:** crear la carpeta datos antes de ejecutarlo 

También tendrán que cambiar a sus direcciones,  y ya debería correr perfectamente el proyecto del backend en el puerto 8080

Estos comandos solo se ejecutan una vez, de ahi lo único que harán es usar **docker run MusicaBD** o **docker run MusicaSpring**

Siempre verificar que los puertos no esten siendo utilizados por otros servicios.

## Pruebas de Endpoints (Ejemplos con curl)

A continuación se muestran ejemplos para probar los endpoints principales de la API utilizando `curl`. Asegúrate de que el servidor esté corriendo en `localhost:8080`.

### CRUD de Usuarios

### 1. Registrar Usuario
Crea un nuevo usuario en la base de datos.

```bash
curl -v -X POST http://localhost:8080/api/usuarios/registro \
-H 'Content-Type: application/json' \
-d '{
    "nombre": "Hiram Test",
    "correo": "hiram@test.com",
    "password": "123",
    "fotoUrl": "[https://ibb.co/0jF6YrwV](https://ibb.co/0jF6YrwV)"
}'
```
### CRUD de Canciones

### 2. Crear Canción
Agrega una nueva canción a la base de datos.

```bash
curl -v -X POST http://localhost:8080/api/canciones \
-H 'Content-Type: application/json' \
-d '{
    "nombre": "Canción de Prueba",
    "duracion_segundos": 210,
    "calificacion": 0.0,
    "descripcion": "Esta es una canción para probar el backend",
    "fecha_salida": 2024,
    "portada_url": "[https://ibb.co/Y7zJyD1p](https://ibb.co/Y7zJyD1p)"
}'
```
### CRUD de Reseñas

### 3. Crear Reseña
Crea una reseña vinculando un usuario y una canción (o álbum) existentes.

```bash
curl -v -X POST http://localhost:8080/api/resenas \
-H 'Content-Type: application/json' \
-d '{
    "contenido": "El backend funciona de maravilla",
    "calificacion": 5.0,
    "usuario": { "id": "3a725921-7f8e-4ee2-ac22-99589865af62" },
    "cancion": { "id": "3d765b8b-30ea-4647-85fb-2f26ffbcd12b" }
}'
```

### 4. Obtener Reseñas
Obtiene la lista de todas las reseñas guardadas.

```bash
curl -v -X GET http://localhost:8080/api/resenas
```

Respuesta esperada (JSON):
```json
[
  {
    "id": "d2b32b1c-81ca-4200-8281-419ce0d58137",
    "contenido": "El backend funciona de maravilla",
    "calificacion": 5.0,
    "fechaCreacion": "2025-12-08",
    "usuario": {
      "id": "3a725921-7f8e-4ee2-ac22-99589865af62",
      "nombre": "Hiram Test",
      "correo": "hiram@test.com",
      "fotoUrl": "[https://ibb.co/0jF6YrwV](https://ibb.co/0jF6YrwV)"
    }
  }
]
```

### CRUD de Artista

### 5. Agregar Artista (POST)

```bash
curl -X POST http://localhost:8080/api/artistas \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Marco Antonio Solis",
    "fecha_inicio": 1970,
    "fecha_fin": null,
    "nacimiento": "1959-12-29",
    "bibliografia": "Conocido como El Buki, es un músico, cantante, compositor y productor musical mexicano.",
    "foto_url": "https://i.ibb.co/chNw5p0h/Artista-marco-antonio-solis.jpg"
  }'
```

### 6. Consultar todos los Artistas (GET)

```bash
curl -X GET http://localhost:8080/api/artistas
```

### CRUD de Álbumes

### 7. Agregar Álbum (POST)
Crea un nuevo álbum y lo vincula con un artista existente. 
**Nota:** Es importante enviar el arreglo de `artistas` con al menos un objeto que contenga el `id` de un artista ya registrado.

```bash
curl -X POST http://localhost:8080/api/albums \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Romance",
    "duracion_segundos": 2700,
    "descripcion": "Un album de boleros producido por Armando Manzanero.",
    "fechaSalida": 1991,
    "totalCanciones": 12,
    "portadaUrl": "[https://i.ibb.co/album-romance.jpg](https://i.ibb.co/album-romance.jpg)",
    "artistas": [
      { "id": "da5f253f-75cd-436f-be91-cc0db25a1325" }
    ]
  }'
```

Respuesta esperada (JSON):
```json
[
  {
    "id": "c9f12345-1234-1234-1234-123456789abc",
    "nombre": "Romance",
    "duracion_segundos": 2700,
    "descripcion": "Un álbum de boleros producido por Armando Manzanero.",
    "calificacion": null,
    "totalCanciones": 12,
    "fechaSalida": 1991,
    "portadaUrl": "[https://i.ibb.co/album-romance.jpg](https://i.ibb.co/album-romance.jpg)",
    "artistas": [
      {
        "id": "pega-aqui-el-uuid-del-artista",
        "nombre": "Luis Miguel",
        "fecha_inicio": 1982
      }
    ],
    "canciones": []
  }
]
```

### 8. Consultar todos los Álbumes (GET)
Obtiene la lista de todos los álbumes registrados en la base de datos.

```bash
curl -v -X GET http://localhost:8080/api/albums
```

##  Despliegue en Producción con Railway

Para llevar este proyecto a internet usaremos **Railway**, ya que detecta automáticamente el `Dockerfile` y facilita la conexión con la base de datos.

### 1. Crear la Base de Datos
1. Entra a [Railway.app](https://railway.app/) y crea un **Nuevo Proyecto**.
2. Selecciona **"Provision PostgreSQL"**.
3. Espera unos segundos a que se cree la base de datos.
4. Haz clic en la tarjeta de PostgreSQL, ve a la pestaña **Variables** y ten a la mano los valores (Host, Database, User, Password).

### 2. Desplegar el Backend
1. En el mismo proyecto, haz clic en el botón **+ New** -> **GitHub Repo**.
2. Selecciona el repositorio `Backend_Musica`.
3. Railway detectará automáticamente el archivo `Dockerfile` en la raíz y comenzará a construir (Build). 
   * **Nota:** La primera vez *fallará* o se quedará cargando porque le faltan los datos de conexión a la base de datos. Esto es normal.

### 3. Configurar Variables de Entorno (Environment Variables)
Para que Spring Boot se conecte a Postgres en la nube, debemos configurar las "llaves" de acceso.

Ve a la tarjeta de tu Backend -> Pestaña **Variables** -> **New Variable** y agrega las siguientes 3:

| Variable | Valor  |
| :--- | :--- |
| `DB_USER` | Copia el valor `PGUSER` de tu servicio de Postgres. |
| `DB_PASSWORD` | Copia el valor `PGPASSWORD` de tu servicio de Postgres. |
| `DB_URL` |  Debes armarla manualmente así:<br>`jdbc:postgresql://HOST_DE_RAILWAY:PUERTO/NOMBRE_BD`<br><br>*Ejemplo:* `jdbc:postgresql://viaduct.proxy.rlwy.net:5432/railway` |

> **Nota Importante sobre `DB_URL`:** Railway te da una URL que empieza con `postgres://...`, pero Spring Boot necesita que empiece con `jdbc:postgresql://...`. Por eso es mejor copiar el **Host** y el **Puerto** de Postgres y armar la URL tú mismo.

### 4. Configurar el Puerto
Por defecto, nuestro Dockerfile expone el puerto `8080`.
1. Ve a la pestaña **Settings** de tu servicio Backend.
2. Busca la sección **Networking**.
3. Asegúrate de que el **PORT** esté configurado en `8080`.
4. Haz clic en **"Generate Domain"** para obtener tu link público (ej: `backend-musica-production.up.railway.app`).

---
