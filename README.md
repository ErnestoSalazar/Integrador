# Integrador
Web service, Web, Desktop and mobile clients for a school project

## Web service

Endpoints    | HTTP verbs   | Examples
------------ | -------------|-------------
__/login__            | __POST__                | form: __grant_type: password, username: correo, password: myPassword__
__/login/recover__    | __POST__                | __{mailTo: correo}__
__/api/users__        | __POST/GET__            | __{nombre, apellido, correo, rfc, rol}__
__/api/users?__       | __GET__                 | __rol=rol__ *or* __nombre=nombre&apellido=apellido__
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
    "access_token": "3Ze_IidQ6V4W6r6tU573qLtdUQNL58mKduW6umivfypfImTPg-HxrgvqvFGGRhyfg5hP0JV-L2D3Y9Sh7iGjMex-ROBHMC0XoL_9IBjtQqt1SDyW9B31Xn_LEITH-V7ddHVcfSMhcNCp_sk4-HGBL82zf_AaHJpNzOlCKAJZ77p0V4AZZ1YT7foMn5hnLWiXKWWYYWz-3Y66xuFLNnvARa1zSKy0zHXtwT089JkooI3cbJqf5n2SLFd9Y6StMg7r0cPI3QvFt3tkawqGz2DMsKSB_HW2najo88ahPHH6GX74fpTxwixGBMiqZpOSHqqMYqbuXC_7rs5GcZVcMzi2THpM6UYWCYjKfHsmb1VtxL-n0UeokvqjUFIYcJQ-v3fLZdPsB8dyLzDWnnOnQjRedA",
    "token_type": "bearer",
    "expires_in": 86399,
    "userName": "correo@example.com",
    "rol": "Admin",
    "userId": "1",
    "nombre": "Ernesto",
    "apellido": "Salazar",
    ".issued": "Fri, 24 Nov 2017 22:24:27 GMT",
    ".expires": "Sat, 25 Nov 2017 22:24:27 GMT"
}
```
in case of a failed login you will receive a status code of __*400*__ and a json with error description
__*otherwise*__
you will need to keep the *access_token*, this one needs to be sended on the header of every request made to __/api/*__
with the key/value:

__Authorization : bearer *access_token*__


### Status
#### Ok
* *When creating any new entity you will get a status code of __201__* and a json response with inserted data

* *When retrieving any entity you will get a status code of __200__* and json response or an array of jsons if retrieving a list of entities

* *When updating any entity you will get a status code of __204__*

* *When deleting any entity you will get a status code of __204__*

#### Error
* *When retrieving any entity or list of entities that does not exist you will get a status of __404__*
* *When login fail you will get a status code of __400__* and a json response with error description

### Users
<details>
<summary>detalles</summary>
When creating a new user, a password will be created automatically by the server and will be send via email

When retrieving a __single__ usuario you will recieve the following data

```json

    {
        "id": 1,
        "nombre": "Ernesto",
        "apellido": "Salazar",
        "rfc": "sdsdfsd",
        "correo": "correo@example.com",
        "rol": "Admin"
    }
```

When retrieving __multiple__ usuarios you will recieve the following data
```json
[
    {
        "id": 1,
        "nombre": "Ernesto",
        "apellido": "Salazar",
        "rfc": "sdsdfsd",
        "correo": "correo@example.com",
        "rol": "Admin"
    }
]
```

When making a GET call to __/api/users?nombre=example&apellido=example__ you will recieve the following data that matches the user nombre and apellido
```json
[
    {
        "id": 32,
        "nombre": "Ernesto",
        "apellido": "Salazar",
        "rfc": "sdsdfsd",
        "correo": "ernestoalbertosalazar@gmail.com",
        "rol": "Admin"
    }
]
```

When making a GET call to __/api/users?rol=example__ you will recieve the following data that matches the user role
```json
[
    {
        "id": 35,
        "nombre": "Isael",
        "apellido": "Atondo",
        "rfc": "isael",
        "correo": "isaelatondo@gmail.com",
        "rol": "Pescador"
    }
]
```

Accepted values for the following properties are:
* __Rol__: *Administrador, Supervisor, Pescador*

</details>



### Barcos
<details>
<summary>Detalles</summary>

When retrieving a __single__ barco you will recieve the following data

```json
    {
        "id": 1,
        "nombre": "barco 1",
        "descripcion": "BARCO_CAMARONERO",
        "usuarioId": 1,
        "usuario": {
            "id": 1,
            "nombre": "Ernesto",
            "apellido": "Salazar",
            "rfc": "sdsdfsd",
            "correo": "correo@example.com",
            "rol": "Admin",
        }
    }
```

When retrieving __multiple__ barcos you will recieve the following data

```json
[
    {
        "id": 1,
        "nombre": "barco 1",
        "descripcion": "BARCO_CAMARONERO",
        "usuarioId": 1,
        "usuario": {
            "id": 1,
            "nombre": "Ernesto",
            "apellido": "Salazar",
            "rfc": "sdsdfsd",
            "correo": "correo@example.com",
            "rol": "Admin"
        }
    }
]
```

</details>

### Cargas
<details>
<summary>detalles</summary>

When retrieving a __single__ carga you will recieve the following data

```json
    {
        "id": 1,
        "cantidad": 150,
        "especie": "Japonesa",
        "talla": "m",
        "temperatura": 99.5,
        "condicion": "Regular",
        "barcoId": 1,
        "barco": {
            "id": 1,
            "nombre": "barco 1",
            "descripcion": "BARCO_CAMARONERO",
            "usuarioId": 1,
            "usuario": null
        },
        "entradaId": 1
    }
```

When retrieving __multiple__ cargas you will recieve the following data

```json
[
    {
        "id": 1,
        "cantidad": 150,
        "especie": "Japonesa",
        "talla": "m",
        "temperatura": 99.5,
        "condicion": "Regular",
        "barcoId": 1,
        "barco": {
            "id": 1,
            "nombre": "barco 1",
            "descripcion": "BARCO_CAMARONERO",
            "usuarioId": 1,
            "usuario": {
                "id": 1,
                "nombre": "Ernesto",
                "apellido": "Salazar",
                "rfc": "sdsdfsd",
                "correo": "correo@example.com",
                "rol": "Admin"
            }
        },
        "entradaId": 1
    }
]
```

Accepted values for the following properties are:
* __Especie__: *Macarela, Japonesa, Monterrey, Rayadillo, Bocona, Anchoveta, Crinuda*
* __Talla__: *S, M, L, XL*
* __Condicion__: *Mala, Regular, Buena*

</details>


### Entradas
<details>
<summary>Detalles</summary>

When retrieving a __single__ entrada you will recieve the following data

```json
    {
    "id": 1,
    "folio": "20171124_153209",
    "fecha": "2017-11-24T00:00:00",
    "hora": "15:32:00",
    "turno": "Vespertino",
    "usuarioId": 1,
    "usuario": {
        "id": 1,
        "nombre": "Ernesto",
        "apellido": "Salazar",
        "rfc": "sdsdfsd",
        "correo": "correo@example.com",
        "rol": "Admin"
    },
    "cargas": [
        {
            "id": 1,
            "cantidad": 150,
            "especie": "Japonesa",
            "talla": "m",
            "temperatura": 99.5,
            "condicion": "Regular",
            "barcoId": 1,
            "barco": null,
            "entradaId": 1
        }
    ],
    "totalMacarela": 0,
    "totalJaponesa": 150,
    "totalMonterrey": 0,
    "totalRayadillo": 0,
    "totalBocona": 0,
    "totalAnchoveta": 0,
    "totalCrinuda": 0,
    "porcentajeMacarela": 0,
    "porcentajeJaponesa": 100,
    "porcentajeMonterrey": 0,
    "porcentajeRayadillo": 0,
    "porcentajeBocona": 0,
    "porcentajeAnchoveta": 0,
    "porcentajeCrinuda": 0,
    "totales": 150
}
```

When retrieving __multiple__ entradas you will recieve the following data

```json
[
    {
        "id": 1,
        "folio": "20171124_153209",
        "fecha": "2017-11-24T00:00:00",
        "hora": "15:32:00",
        "turno": "Vespertino",
        "usuarioId": 1,
        "usuario": {
            "id": 1,
            "nombre": "Ernesto",
            "apellido": "Salazar",
            "rfc": "sdsdfsd",
            "correo": "correo@example.com",
            "rol": "Admin"
        },
        "cargas": [
            {
                "id": 1,
                "cantidad": 150,
                "especie": "Japonesa",
                "talla": "m",
                "temperatura": 99.5,
                "condicion": "Regular",
                "barcoId": 1,
                "barco": null,
                "entradaId": 1
            }
        ]
        
    }
]
```


Accepted values for the following properties are:
* __Turno__: *Matutino, Vespertino*
</details>

### Entradas by date
<details>
<summary>Detalles</summary>
When searching an entrada by a date you will receive a json array with the following data that matches with those dates parameters

```json
[
    {
        "id": 1,
        "folio": "20171124_153209",
        "fecha": "2017-11-24T00:00:00",
        "hora": "15:32:00",
        "turno": "Vespertino",
        "usuarioId": 1,
        "usuario": {
            "id": 1,
            "nombre": "Ernesto",
            "apellido": "Salazar",
            "rfc": "sdsdfsd",
            "correo": "correo@example.com",
            "rol": "Admin"
        },
        "cargas": [
            {
                "id": 1,
                "cantidad": 150,
                "especie": "Japonesa",
                "talla": "m",
                "temperatura": 99.5,
                "condicion": "Regular",
                "barcoId": 1,
                "barco": null,
                "entradaId": 1
            }
        ]
    }
]
```


</details>