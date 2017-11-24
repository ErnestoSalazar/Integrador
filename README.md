# Integrador
Web service, Web, Desktop and mobile clients for a school project

## Web service

Endpoints    | HTTP verbs   | Examples
------------ | -------------|-------------
__/login__            | __POST__                | form: __grant_type: password, username: correo, password: myPassword__
__/login/recover__    | __POST__                | __{mailTo: correo}__
__/api/users__        | __POST/GET__            | __{nombre, apellido, correo, rfc, rol}__
__/api/users?__       | __GET__                 | __rol=rol__
__/api/users/{id}__   | __GET/PUT/DELETE__      | __{nombre, apellido, correo, rfc, rol, password}__
__/api/barcos__       | __POST/GET__            | __{nombre, descripcion, usuarioId}__
__/api/barcos/{id}__  | __GET/PUT/DELETE__      | __{nombre, descripcion, usuarioId}__
__/api/cargas__       | __POST/GET__            | __{cantidad, especie, talla, temperatura, condicion, barcoId}__
__/api/cargas/{id}__  | __GET/PUT/DELETE__      | __{cantidad, especie, talla, temperatura, condicion, barcoId}__
__/api/entradas__     | __POST/GET__            | __{usuarioId, cargasId:[]}__
__/api/entradas?__    | __GET__                 | __fechaInicio=yyyy/mm/dd&fechaFin=yyyy/mm/dd__ *or* __folio=folio__
__/api/entradas/{id}__| __GET/PUT/DELETE__      | __{cargasId:[]}__


### Login
after Login you will recieve a response like this :
```json
{
    "access_token": "kbN51O4put0_Hxg75LUgQaD2rMKys_CnDcfkflAGddJH0wnUSHszYiq6VVm-Q-x8tKp8HlQrgbfyGg9QpynEEL3KfUdAqDL9CrC26f5cd8q2qavYO6Xv9efrRBOiLp17fdaIgi31pjegwfwIv5JQuVf12KaddQKVSTlGzcWavlVQgAi_qwRr0hAZEqlB45EA84F5ZDdITMF1T79cMhBDarAMcJbfQ_ezlwnj2fn30WbtwMwVt9HoeT24iSgFHTcgaX8R_iE2vf4uAU55xgcER8NaRxw0UPHf0ZxaDu95oTJl6zOaItLjbnkhrinnm4ueVm9XpLxfLZI75QFu8-jeIICfRQ79MXyPBP1gP3EEc43cjZ8a9CR_WGxVHteJjgyT3EHN2xqmlUeRsTfpVKjDMg",
    "token_type": "bearer",
    "expires_in": 86399,
    "userName": "correo@example.com",
    "rol": "Admin",
    ".issued": "Thu, 23 Nov 2017 20:39:14 GMT",
    ".expires": "Fri, 24 Nov 2017 20:39:14 GMT"
}
```
in case of a failed login you will receive a status code of __*400*__ and a json with error description
__*otherwise*__
you will need to keep the *access_token*, this one needs to be sended on the header of every request made to __/api/*__
with the key/value:

__Authorization : bearer *access_token*__


### Status
#### Ok
* *When updating any entity you will get a status code of __204__*

* *When creating any new entity you will get a status code of __201__* and a json response with inserted data

* *when retrieving any entity you will get a status code of __200__* and json response or an array of jsons if retrieving a list of entities

#### Error
* *When retrieving any entity or list of entities that does not exist you will get a status of __404__*
* *When login fail you will get a status code of __400__* and a json response with error description

### Users
When creating a new user, a password will be created automatically by the server and will be send via email

Accepted values for the following properties are:
* __Rol__: *Admin, Supervisor, Pescador*

### Cargas
Accepted values for the following properties are:
* __Especie__: *Macarela, Japonesa, Monterrey, Rayadillo, Bocona, Anchoveta, Crinuda*
* __Talla__: *s, m, l, xl*
* __Condicion__: *Mala, Regular, Buena*

### Entradas
Accepted values for the following properties are:
* __Turno__: *Matutino, Vespertino*