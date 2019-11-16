# consumeAPI-Earthquake
Consumo de APIS desde el servicio del science for a changing world https://earthquake.usgs.gov/
La tecnologia utilizada es:
* Java 8
* Spring Boot
* Gradle 
* Spock
* Groovy

#Run Test Project
Para ejecutar la tarea de pruebas de Spock debe usar el comando:
* gradle clean test, este ejecuta las pruebas de spock y crea un archivo html en la ruta \build\reports\tests\test
 ```bash
 gradle clean test
 ```
 
# Ruta de Logs
El programa tiene una ruta para escribir los logs la cual es: 
* C:/logs/APPS/EARTHQUAKE/earthquake.log"

## Endpoints
Endpoints publicos para la ejecucion de los servicios REST

## Swagger (http://localhost:9090/earthquake/swagger-ui.html#/)
Web para obtener la documentacion de las APIS en Swagger

WEB: http://localhost:9090/earthquake/swagger-ui.html#/



## [/service/obtainbyfecha](http://localhost:9090/earthquake/service/obtainbyfecha)

Endpoint encargado de obtener los temblores y terremotos con una fecha inicial y una fecha final

**HTTP Method:** POST

**Endpoint:** http://localhost:9090/earthquake/service/obtainbyfecha

| Attribute | Required |Type | Note | Example |
------------|----------|-----|------|--------|
| Fecha Inicial  | Yes | String | Fecha requerida en formato YYYY-MM-DD |2019-10-01 |
| Fecha Final | Yes | String | Fecha requerida en formato YYYY-MM-DD | 2019-10-03 |

### Valid request
```json
{
	"starttime": "2019-10-01",
	"endtime": "2019-10-03"
}
```

## [/service/obtainbymagnitud](http://localhost:9090/earthquake/services/obtainbymagnitud)

Endpoint encargado de obtener los temblores y terremotos con una magnitud inicial y una magnitud final

**HTTP Method:** POST

**Endpoint:** http://localhost:9090/earthquake/services/obtainbymagnitud

| Attribute | Required |Type | Note | Example |
------------|----------|-----|------|--------|
| Magnitud Inicial  | Yes | Double | Fecha requerida en formato YYYY-MM-DD |2019-10-01 |
| Magnitud Final | Yes | Double | Fecha requerida en formato YYYY-MM-DD | 2019-10-03 |

### Valid request
```json
{
	"magnitudIni": 6.5,
	"magnitudFin": 7
}
```

## [/services/obtainbytwodates](http://localhost:9090/earthquake/services/obtainbytwodates)

Endpoint encargado de obtener los temblores y terremotos con dos fechas inicial y dos fechas final

**HTTP Method:** POST

**Endpoint:** http://localhost:9090/earthquake/services/obtainbytwodates

| Attribute | Required |Type | Note | Example |
------------|----------|-----|------|--------|
| Fecha Inicial  | Yes | String | Fecha requerida en formato YYYY-MM-DD |2019-10-01 |
| Fecha Final | Yes | String | Fecha requerida en formato YYYY-MM-DD | 2019-10-03 |
| Fecha Inicial 2 | Yes | String | Fecha requerida en formato YYYY-MM-DD |2019-10-06 |
| Fecha Final 2 | Yes | String | Fecha requerida en formato YYYY-MM-DD | 2019-10-14 |

### Valid request
```json
{
	"starttime": "2019-10-01",
	"endtime": "2019-10-03",
	"starttime2": "2019-10-06",
	"endtime2": "2019-10-14"
}
```

## [/services/obtainbycountry](http://localhost:9090/earthquake/services/obtainbycountry)

Endpoint encargado de obtener los temblores y terremotos con dos fechas inicial y dos fechas final

**HTTP Method:** POST

**Endpoint:** http://localhost:9090/earthquake/services/obtainbycountry

| Attribute | Required |Type | Note | Example |
------------|----------|-----|------|--------|
| Pais  | Yes | String | Pais de entrada |Alaska |

### Valid request
```json
{
	"country":"Alaska"
}
```

## [/services/obtainbydateandcountry](http://localhost:9090/earthquake/services/obtainbydateandcountry)

Endpoint encargado de obtener los temblores y terremotos con dos fechas inicial y dos fechas final

**HTTP Method:** POST

**Endpoint:** http://localhost:9090/earthquake/services/obtainbydateandcountry

| Attribute | Required |Type | Note | Example |
------------|----------|-----|------|--------|
| Pais  | Yes | String | Pais de entrada |Alaska |
| Pais2  | Yes | String | Pais de entrada |Indonesia |
| Fecha Inicial  | Yes | String | Fecha requerida en formato YYYY-MM-DD |2019-10-01 |
| Fecha Final | Yes | String | Fecha requerida en formato YYYY-MM-DD | 2019-10-03 |
| Fecha Inicial 2 | Yes | String | Fecha requerida en formato YYYY-MM-DD |2019-10-06 |
| Fecha Final 2 | Yes | String | Fecha requerida en formato YYYY-MM-DD | 2019-10-14 |


### Valid request
```json
{
	"starttime": "2019-10-01",
	"endtime": "2019-10-03",
	"starttime2": "2019-10-06",
	"endtime2": "2019-10-14",
	"country":"Alaska",
	"country":"Indonesia"
}
```