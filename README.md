### Gestor de estatiditicas

El objetivo de este proyecto es obtener una estadistica del lugar con más neightbirdhoods , asi como 
la ciudad mas al sur donde opera con sus respectivas localidades.

### Para correr el proyecto localmento 

- Ejecutar el comando mvn spring-boot:run

### Para iniciar el proyecto con docker ejecutar las siguientes instrucciones 

- docker build . --tag springboot_app_gestor:1.0
- docker run --p 8000:8080 --d --name gestor_stats springboot_app_gestor:1.0

-   Para chequear si el proyecto esta corriendo :
    - Ejecutar el sigueinte endpoint 
    ```
       curl -H "Content-Type: application/json" -X  GET  localhost:8080/42i/gestor/stats/v1/isOk
    ```
- Para obtener la localidad con mas neightbirdhoods ejecutar el siguiente endpoint

    ```
    curl -H "Content-Type: application/json" -X GET  localhost:8080/42i/gestor/stats/v1/topLocality
    
    ```
- Obtener los neightbirdhoods de una  localidad ejecutar el siguiente endpoint
    - Es obligatorio para el paramtro locality

    ```
    curl -H "Content-Type: application/json" -X GET  localhost:8080/42i/gestor/stats/v1/searchNeightBirdHoods?locality="Manhatan"
    
    ```

- Para obtner la localidad mas lejana ejecutar el siguiente y las localidades alrededor ejecutar

    ```
    curl -H "Content-Type: application/json" -X GET  localhost:8080/42i/gestor/stats/v1/futherAwayLocations
    
    ```
