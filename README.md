
---

## Reseña del Proyecto

El proyecto **AutomotoresSala** es una aplicación diseñada para gestionar una concesionaria de automóviles. Permite a los usuarios realizar el alta, baja, modificación y listado de vehículos en stock, así como gestionar sus principales características, incluyendo fotos. Además, la aplicación facilita la administración de marcas y propietarios de los vehículos. También incluye funcionalidades para la gestión de usuarios y roles, asegurando que solo los usuarios autorizados puedan realizar ciertas acciones.

### Problema que Resuelve

El proyecto resuelve el problema de la gestión eficiente de información en una concesionaria de automóviles. Facilita la organización y el acceso a los datos, permitiendo a los usuarios realizar operaciones de manera sencilla y segura. La aplicación asegura que solo los usuarios autorizados puedan realizar ciertas acciones, mejorando la seguridad y la integridad de los datos.

## Tecnologías y Librerías Utilizadas

- **Java**: Lenguaje de programación principal utilizado para desarrollar la aplicación.
- **Spring Boot**: Framework utilizado para crear aplicaciones Java basadas en Spring de manera rápida y sencilla.
- **Spring Data JPA**: Proporciona abstracciones de persistencia de datos sobre JPA (Java Persistence API).
- **Spring Security**: Framework de seguridad que proporciona autenticación y autorización.
- **Hibernate**: Implementación de JPA utilizada para la persistencia de datos.
- **Lombok**: Librería que reduce el código boilerplate mediante anotaciones.
- **MySQL**: Base de datos relacional utilizada para almacenar los datos de la aplicación.
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.
- **JUnit**: Framework de pruebas unitarias para Java.
- **Mockito**: Framework de simulación para pruebas unitarias en Java.
- **Swagger**: Herramienta para documentar y probar APIs RESTful.

---

# Controladores y Endpoints

## BrandController

| Método               | Endpoint               | Método HTTP | URI               | Request Params | Request Body         | Response Body        | Excepciones                  |
|----------------------|------------------------|-------------|-------------------|----------------|----------------------|----------------------|------------------------------|
| getAllBrands         | getAllBrands           | GET         | /brands           |                |                      | List<BrandDTO>       |                              |
| getBrandById         | getBrandById           | GET         | /brands/{id}      |                |                      | BrandDTO             | BrandNotFoundException       |
| createBrand          | createBrand            | POST        | /brands           |                | CreateBrandRequest   | BrandDTO             |                              |
| updateBrand          | updateBrand            | PUT         | /brands/{id}      |                | UpdateBrandRequest   | BrandDTO             | BrandNotFoundException       |
| deleteBrand          | deleteBrand            | DELETE      | /brands/{id}      |                |                      | void                 | BrandNotFoundException       |

## OwnerController

| Método               | Endpoint               | Método HTTP | URI               | Request Params | Request Body         | Response Body        | Excepciones                  |
|----------------------|------------------------|-------------|-------------------|----------------|----------------------|----------------------|------------------------------|
| getAllOwners         | getAllOwners           | GET         | /owners           |                |                      | List<OwnerDTO>       |                              |
| getOwnerById         | getOwnerById           | GET         | /owners/{id}      |                |                      | OwnerDTO             | OwnerNotFoundException       |
| createOwner          | createOwner            | POST        | /owners           |                | CreateOwnerRequest   | OwnerDTO             |                              |
| updateOwner          | updateOwner            | PUT         | /owners/{id}      |                | UpdateOwnerRequest   | OwnerDTO             | OwnerNotFoundException       |
| deleteOwner          | deleteOwner            | DELETE      | /owners/{id}      |                |                      | void                 | OwnerNotFoundException       |

## VehicleController

| Método               | Endpoint               | Método HTTP | URI               | Request Params | Request Body         | Response Body        | Excepciones                  |
|----------------------|------------------------|-------------|-------------------|----------------|----------------------|----------------------|------------------------------|
| getAllVehicles       | getAllVehicles         | GET         | /vehicles         |                |                      | List<VehicleDTO>     |                              |
| getVehicleById       | getVehicleById         | GET         | /vehicles/{id}    |                |                      | VehicleDTO           | VehicleNotFoundException     |
| createVehicle        | createVehicle          | POST        | /vehicles         |                | CreateVehicleRequest | VehicleDTO           |                              |
| updateVehicle        | updateVehicle          | PUT         | /vehicles/{id}    |                | UpdateVehicleRequest | VehicleDTO           | VehicleNotFoundException     |
| deleteVehicle        | deleteVehicle          | DELETE      | /vehicles/{id}    |                |                      | void                 | VehicleNotFoundException     |

# Clases, DTOs y Request Objects

## Brand

| Campo    | Tipo               | Descripción                        |
|----------|--------------------|------------------------------------|
| id       | Long               | Identificador único de la marca    |
| name     | String             | Nombre de la marca                 |
| vehicles | List<Vehicle>      | Lista de vehículos asociados a la marca |

## Vehicle

| Campo   | Tipo   | Descripción                        |
|---------|--------|------------------------------------|
| id      | Long   | Identificador único del vehículo   |
| model   | String | Modelo del vehículo                |
| brandId | Brand  | Marca asociada al vehículo         |

## Owner

| Campo    | Tipo               | Descripción                        |
|----------|--------------------|------------------------------------|
| id       | Long               | Identificador único del propietario |
| name     | String             | Nombre del propietario             |
| vehicles | List<Vehicle>      | Lista de vehículos del propietario |

## BrandDTO

| Campo    | Tipo               | Descripción                        |
|----------|--------------------|------------------------------------|
| id       | Long               | Identificador único de la marca    |
| name     | String             | Nombre de la marca                 |
| vehicles | List<VehicleDTO>   | Lista de vehículos asociados a la marca |

## VehicleDTO

| Campo   | Tipo   | Descripción                        |
|---------|--------|------------------------------------|
| id      | Long   | Identificador único del vehículo   |
| model   | String | Modelo del vehículo                |
| brandId | Long   | Identificador de la marca asociada |

## OwnerDTO

| Campo    | Tipo               | Descripción                        |
|----------|--------------------|------------------------------------|
| id       | Long               | Identificador único del propietario |
| name     | String             | Nombre del propietario             |
| vehicles | List<VehicleDTO>   | Lista de vehículos del propietario |

## CreateBrandRequest

| Campo | Tipo   | Descripción            |
|-------|--------|------------------------|
| name  | String | Nombre de la marca     |

## UpdateBrandRequest

| Campo | Tipo   | Descripción            |
|-------|--------|------------------------|
| name  | String | Nombre de la marca     |

## CreateVehicleRequest

| Campo   | Tipo   | Descripción                        |
|---------|--------|------------------------------------|
| model   | String | Modelo del vehículo                |
| brandId | Long   | Identificador de la marca asociada |

## UpdateVehicleRequest

| Campo   | Tipo   | Descripción                        |
|---------|--------|------------------------------------|
| model   | String | Modelo del vehículo                |
| brandId | Long   | Identificador de la marca asociada |

## CreateOwnerRequest

| Campo | Tipo   | Descripción            |
|-------|--------|------------------------|
| name  | String | Nombre del propietario |

## UpdateOwnerRequest

| Campo | Tipo   | Descripción            |
|-------|--------|------------------------|
| name  | String | Nombre del propietario |
