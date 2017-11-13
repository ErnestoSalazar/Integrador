# Integrador
School project


Endpoints    | HTTP verbs   | Examples
------------ | -------------|-------------
__/login__            | __POST__                | form: __grant_type: password, username: myUsername, password: myPassword__
__/login/recover__    | __POST__                | __{mailTo: myEmail}__
__/api/users__        | __POST/GET__            | __{nombre, apellido, correo, rfc, rol}__
__/api/users/{id}__   | __GET/PUT/DELETE__      | __{nombre, apellido, correo, rfc, rol, password}__
 __/api/barcos__      | __POST/GET__            | __{nombre, descripcion, ususarioId}__
__/api/barcos/{id}__  | __GET/PUT/DELETE__      | __{nombre, descripcion, ususarioId}__
__/api/cargas__       | __POST/GET__            | __{cantidad, especie, talla, temperatura, condicion, barcoId}__
__/api/cargas/{id}__  | __GET/PUT/DELETE__      | __{cantidad, especie, talla, temperatura, condicion, barcoId}__
__/api/entradas__     | __POST/GET__            | __{folio, turno, usuarioId, cargasId:[]}__
__/api/entradas{id}__ | __GET/PUT/DELETE__      | __{folio, turno, usuarioId, cargasId:[]}__