# Integrador
Web service, Web, Desktop and mobile clients for a school project

## Web service

Endpoints    | HTTP verbs   | Examples
------------ | -------------|-------------
__/login__            | __POST__                | form: __grant_type: password, username: correo, password: myPassword__
__/login/recover__    | __POST__                | __{mailTo: correo}__
__/api/users__        | __POST/GET__            | __{nombre, apellido, correo, rfc, rol}__
__/api/users/{id}__   | __GET/PUT/DELETE__      | __{nombre, apellido, correo, rfc, rol, password}__
__/api/barcos__       | __POST/GET__            | __{nombre, descripcion, usuarioId}__
__/api/barcos/{id}__  | __GET/PUT/DELETE__      | __{nombre, descripcion, usuarioId}__
__/api/cargas__       | __POST/GET__            | __{cantidad, especie, talla, temperatura, condicion, barcoId}__
__/api/cargas/{id}__  | __GET/PUT/DELETE__      | __{cantidad, especie, talla, temperatura, condicion, barcoId}__
__/api/entradas__     | __POST/GET__            | __{turno, usuarioId, cargasId:[]}__
__/api/entradas?__    | __GET__                 | __fechaInicio=dd/mm/yyyy&fechaFin=dd/mm/yyyy__
__/api/entradas/{id}__ | __GET/PUT/DELETE__      | __{turno, usuarioId, cargasId:[]}__


### Login
after Login you will recieve a response like this :
```json
{
    "access_token": "ZUot4VTE13rKUagahglxVBCPlTnEAR5g82lMbHHlAeS1wPPSbaRtjBqMmA0V00AVtoFku6fKZmMcCyF8fyPztAumSN3Rzxay_G8F5eo4OtHTQ2npEm-ha4qeTqm_CwSyO6LzJF-IgAP2j9hsPfyttiwpBjd6GgYuZq0sBSKXvKvqW4CGQfjCaw2NHdIFqlkvo5fxvaJS8oqNks4dxiMOJ3Xf0Rlj6t2jiLkoO5X1xHPn34NsoLOqqwUe-ZIlq-VsTrUZtadLKk3PRIcezoWcq9nGX3pLmrR-Wi-Kex_6QC55WXVK8beCtC8GPyUS3zykM2GpR04gvfEN84OCCnsS11KodPOOQSugt1mP-KRUVvqcNDGZqvlTZxmP_F8d118Gd02R1-v2tiGshC6B1EEVDg",
    "token_type": "bearer",
    "expires_in": 86399,
    "userName": "name",
    ".issued": "Mon, 13 Nov 2017 15:40:00 GMT",
    ".expires": "Tue, 14 Nov 2017 15:40:00 GMT"
}
```
you will need to keep the *access_token*, this one needs to be sended on the header of every request made to __/api/*__
with the key/value:

__Authorization : bearer *access_token*__


### Users
When creating a new user, a password will be created automatically by the server and will be send via email

Accepted values for the following properties are:
* __Rol__: *Admin, Supervisor, Pescador*

### Cargas
Accepted values for the following properties are:
* __Especie__: *Macarela, Japonesa, Monterrey, Rayadillo, Bocona, Anchoveta, Crinuda*
* __Talla__: *s, m, x, xl*
* __Condicion__: *Mala, Regular, Buena*

### Entradas
Accepted values for the following properties are:
* __Turno__: *Matutino, Vespertino*