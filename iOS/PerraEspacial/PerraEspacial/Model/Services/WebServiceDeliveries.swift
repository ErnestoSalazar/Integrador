//
//  WebServiceDeliveries.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 26/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import Foundation
import Alamofire
import SwiftyJSON

struct WebServiceDeliveries {
    static func getDeliveries(dateBegin : String, dateEnd : String, completionHandler:@escaping (_ status : Bool,_ deliveries : [Delivery])->()){
        //Alamofire Get Request
        
        var urlDeliveries = WebLinks.Service.urlDeliveries
        
        if dateBegin != "" && dateEnd != "" {
            urlDeliveries = "\(WebLinks.Service.urlDeliveries)?fechaInicio=\(dateBegin)&fechaFin=\(dateEnd)"
        }
        Alamofire.request(urlDeliveries, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: WebLinks.headers).responseJSON { (response:DataResponse<Any>) in
            switch response.result {
            case .success(let value):
                let jsonResponse = JSON(value)
                let deliveriesArray = jsonResponse.arrayValue
                var deliveries : [Delivery] = []
                for delivery in deliveriesArray {
                    let id = delivery["id"].int ?? 0
                    let folio = delivery["folio"].string ?? ""
                    let userId = delivery["usuarioId"].int ?? 0
                    let date = delivery["fecha"].string ?? ""
                    let time = delivery["hora"].string ?? ""
                    let turn = delivery["turno"].string ?? ""

                    let user = delivery["usuario"]
                    let idUser = user["id"].int ?? 0
                    let email = user["correo"].string ?? ""
                    let nameUser = user["nombre"].string ?? ""
                    let lastName = user["apellido"].string ?? ""
                    let rfc = user["rfc"].string ?? ""
                    let roleName = user["rol"].string ?? ""
                    let role = Role(id: 0, name: roleName)
                    let userInfo = User(id: idUser, email: email, name: nameUser, lastName: lastName, password: "", rfc: rfc, role: role)
                    
                    let cargasArray = delivery["cargas"].arrayValue
                    var cargas : [Carga] = []
                    for carga in cargasArray {
                        let id = carga["id"].int ?? 0
                        let condition = carga["condicion"].string ?? ""
                        let quantity = carga["cantidad"].double ?? 0.0
                        let temperature = carga["temperatura"].double ?? 0.0
                        let boatId = carga["barcoId"].int ?? 0
                        let especie = carga["especie"].string ?? ""
                        let size = carga["talla"].string ?? ""

                        let boat = carga["barco"]
                        let idBoat = boat["id"].int ?? 0
                        let nameBoat = boat["nombre"].string ?? ""
                        let descriptionBoat = boat["descripcion"].string ?? ""
                        let userIdBoat = boat["usuarioId"].int ?? 0
                        let boatInfo = Boat(id: idBoat, name: nameBoat, description: descriptionBoat, userId: userIdBoat, user: nil)
                        
                        
                        let cargaInfo = Carga(id: id, condition: condition, quantity: quantity, temperature: temperature, boatId: boatId, boat: boatInfo, especie: especie, size: size)
                        cargas.append(cargaInfo)
                    }
                    
                    let deliveryInfo = Delivery(id: id, folio: folio, date: date, time: time, turn: turn, idUser: userId, user: userInfo, cargas: cargas)
                    deliveries.append(deliveryInfo)
                }
                
                
                completionHandler(true,deliveries)
            case .failure(let error):
                print(error)
                completionHandler(false, [])
            }
        }
    }
    
    static func deleteDelivery(idDelivery: Int, completionHandler :@escaping (_ succes: Bool, _ message: String)->()){
        Alamofire.request("\(WebLinks.Service.urlDeliveries)/\(idDelivery)", method: .delete, parameters: nil, encoding: JSONEncoding.default, headers: WebLinks.headers).responseJSON{
            (responseObject) -> Void in
            if (responseObject.result.isSuccess){
                if let httpStatusCode = responseObject.response?.statusCode {
                    if httpStatusCode == 204 {
                        completionHandler(true, "")
                    }else {
                        completionHandler(false, "Ha ocurrido un error (\(httpStatusCode)), por favor intente mas tarde!")
                        print("Error \(httpStatusCode)")
                    }
                }
                
            }else{
                completionHandler(false, "Error de conexión")
            }
        }
    }
    
    
    static func deleteCarga(idCarga: Int, completionHandler :@escaping (_ succes: Bool, _ message: String)->()){
        Alamofire.request("\(WebLinks.Service.urlCargas)/\(idCarga)", method: .delete, parameters: nil, encoding: JSONEncoding.default, headers: WebLinks.headers).responseJSON{
            (responseObject) -> Void in
            if (responseObject.result.isSuccess){
                if let httpStatusCode = responseObject.response?.statusCode {
                    if httpStatusCode == 204 {
                        completionHandler(true, "")
                    }else {
                        completionHandler(false, "Ha ocurrido un error (\(httpStatusCode)), por favor intente mas tarde!")
                        print("Error \(httpStatusCode)")
                    }
                }
                
            }else{
                completionHandler(false, "Error de conexión")
            }
        }
    }
    
    static func createCarga(carga : Carga, completionHandler :@escaping (_ idCarga : Int, _ status: Bool, _ message: String)->()) {
        
        let parameters : Parameters = [
            "cantidad" : carga.quantity,
            "especie" : carga.especie,
            "talla" : carga.size,
            "temperatura" : carga.temperature,
            "condicion" : carga.condition,
            "barcoId" : carga.boat.id
        ]
        
        print("\(parameters)")
        
        Alamofire.request(WebLinks.Service.urlCargas, method: .post, parameters: parameters, encoding: URLEncoding.httpBody, headers: WebLinks.headers).responseJSON { response in
            print("\(response)")
            switch(response.result)
            {
                
            case .success( _):
                let JSON = response.result.value as! NSDictionary
                
                if let httpStatusCode = response.response?.statusCode
                {
                    var message = ""
                    switch(httpStatusCode){
                    case 201:
                        if let idCarga = JSON["id"] as? Int {
                            completionHandler(idCarga, true, message)
                        }
                        
                    default:
                        print("Code: \(httpStatusCode) Mensaje: \(JSON["error_description"] ?? "N/A")")
                        if let messageResponse = JSON["error_description"] {
                            message = messageResponse as! String
                            completionHandler(0, false, message)
                        }
                    }
                }
            case .failure(let error):
                if let httpStatusCode = response.response?.statusCode {
                    completionHandler(0, false, "Ha ocurrido un error (\(httpStatusCode)), por favor intente mas tarde!")
                    print("Error \(httpStatusCode)")
                }
                else {
                    completionHandler(0, false, "Ha ocurrido un error, por favor intente mas tarde!")
                    print(error.localizedDescription)
                }
            }
        }
    }
    
    static func createDelivery(idCargas : [Int], completionHandler :@escaping (_ status: Bool, _ message: String)->()) {
        let parameters : Parameters = [
            "usuarioId" : loggedUser?.id ?? 0,
            "cargasId" : idCargas
        ]
        
        print("\(parameters)")

        
        Alamofire.request(WebLinks.Service.urlDeliveries+"", method: .post, parameters: parameters, encoding: JSONEncoding.default, headers: WebLinks.headers).responseJSON { response in
            print("\(response)")
            switch(response.result)
            {
                
            case .success( _):
                let JSON = response.result.value as! NSDictionary
                
                if let httpStatusCode = response.response?.statusCode
                {
                    var message = ""
                    switch(httpStatusCode){
                    case 201:
                        if let _ = JSON["id"] as? Int {
                            completionHandler(true, "Entrega creada")
                        }
                        
                    default:
                        print("Code: \(httpStatusCode) Mensaje: \(JSON["error_description"] ?? "N/A")")
                        if let messageResponse = JSON["error_description"] {
                            message = messageResponse as! String
                            completionHandler(false, message)
                        }
                    }
                }
            case .failure(let error):
                if let httpStatusCode = response.response?.statusCode {
                    completionHandler(false, "Ha ocurrido un error (\(httpStatusCode)), por favor intente mas tarde!")
                    print("Error \(httpStatusCode)")
                }
                else {
                    completionHandler(false, "Ha ocurrido un error, por favor intente mas tarde!")
                    print(error.localizedDescription)
                }
            }
        }

    }
    
    

    static func editCarga(idCarga : Int, carga : Carga, completionHandler:@escaping (_ status : Bool, _ message : String)->()){
        //Alamofire Put Request
        let parameters : Parameters = [
            "cantidad" : carga.quantity,
            "especie" : carga.especie,
            "talla" : carga.size,
            "temperatura" : carga.temperature,
            "condicion" : carga.condition,
            "barcoId" : carga.boat.id
        ]
        
        Alamofire.request("\(WebLinks.Service.urlCargas)\(idCarga)", method: .put, parameters: parameters, encoding: JSONEncoding.default, headers: WebLinks.headers).responseJSON { (response:DataResponse<Any>) in
            switch response.result {
            case .success( _):
                if let httpStatusCode = response.response?.statusCode {
                    if httpStatusCode == 204 {
                        completionHandler(true, "")
                    }else {
                        completionHandler(false, "Ha ocurrido un error (\(httpStatusCode)), por favor intente mas tarde!")
                        print("Error \(httpStatusCode)")
                    }
                }
                
            case .failure(let error):
                completionHandler(false, "No fue posible editar usuario, favor de interntarlo más tarde")
                print(error)
            }
        }
    }
    
    
}
