# API Movies

Proyecto del bootcamp de Factoria F5 cuyo objetivo es crear una API REST para
gestionar películas.

## Requisitos

La API deberá incluir los siguientes endpoints:

1. Obtener todas las películas.
2. Obtener una película por su identificador (`id`).
3. Añadir una película.
4. Actualizar los datos de una película.
5. Eliminar una película.
6. Crear un sexto endpoint para recuperar una película por su título o género (recuerda utilizar `findBy`).
7. Crear las tablas adicionales de géneros, años y actores con sus respectivas relaciones.

## Modelado de datos

El proyecto debe incluir:

- Un diagrama de Chen.
- Un diagrama de patas de gallo.
- Las tablas de películas, géneros, años y actores.
- Las relaciones entre las entidades, correctamente definidas.

### Requisitos previos

- Maven
- Java 21
- Spring Boot
- Base de datos H2 en memoria
- JPA e Hibernate

## Análisis previo

Tenemos 4 entidades principales: `movie`, `genre`, `year` y `actor`.

### Relaciones

- **Movie N:M Genre**: una película puede tener varios géneros y un género puede clasificar muchas películas.
- **Year 1:N Movie**: un año puede tener muchas películas, pero cada película pertenece a un solo año.
- **Movie N:M Actor**: una película puede contar con varios actores y un actor puede participar en muchas películas.

### Tablas

| Tabla | Descripción |
|--------|-------------|
| `movies` | Información de las películas. |
| `genres` | Catálogo de géneros cinematográficos. |
| `years` | Años de lanzamiento de las películas. |
| `actors` | Catálogo de actores. |
| `movies_genres` | Tabla intermedia para la relación N:M entre películas y géneros. |
| `movies_actors` | Tabla intermedia para la relación N:M entre películas y actores. |

### Diagrama de Chen

![Chen Diagram](docs/assets/chen-diagram.png)

### Diagrama de patas de gallo

![Crow's Foot Diagram](docs/assets/crow-s-foot-diagram.png)


## Referencia de la API

La API está disponible en:

`http://localhost:8080/api/movies`

### Películas

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/api/movies` | Obtener todas las películas |
| `GET` | `/api/movies/{id}` | Obtener una película por su ID |
| `POST` | `/api/movies` | Crear una nueva película |
| `PUT` | `/api/movies/{id}` | Actualizar una película existente |
| `DELETE` | `/api/movies/{id}` | Eliminar una película |
| `GET` | `/api/movies/search/title?title={title}` | Buscar películas por título |
| `GET` | `/api/movies/search/genre?genre={genre}` | Buscar películas por género |

