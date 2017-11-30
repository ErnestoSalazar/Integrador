//
//  WebServiceBoat.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 22/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import Foundation
import Alamofire
import SwiftyJSON

struct WebServiceBoat {
    static func createBoat(boat : Boat, completionHandler :@escaping (_ status: Bool, _ message: String)->()) {
        
        let parameters : Parameters = [
            "nombre" : boat.name,
            "descripcion" : boat.description,
            "usuarioId" : boat.userId
        ]
        
        Alamofire.request(WebLinks.Service.urlBoats, method: .post, parameters: parameters, encoding: URLEncoding.httpBody, headers: WebLinks.headers).responseJSON { response in
            switch(response.result)
            {
                
            case .success( _):
                let JSON = response.result.value as! NSDictionary
                
                print("PARAMETERS: \(parameters)")
                print("JSON: \(JSON)")
                
                if let httpStatusCode = response.response?.statusCode
                {
                    var message = ""
                    switch(httpStatusCode){
                    case 201:
                        if let _ = JSON["id"] {
                            completionHandler(true, message)
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
    
    static func getBoats(completionHandler:@escaping (_ status : Bool,_ boats : [Boat])->()){
        //Alamofire Get Request
        
        Alamofire.request(WebLinks.Service.urlBoats, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: WebLinks.headers).responseJSON { (response:DataResponse<Any>) in
            switch response.result {
            case .success(let value):
                let jsonResponse = JSON(value)
                let boatsArray = jsonResponse.arrayValue
                var boatsInfo : [Boat] = []
                
                print(jsonResponse)
                
                for boat in boatsArray {
                    let id = boat["id"].int ?? 0
                    let name = boat["nombre"].string ?? ""
                    let description = boat["descripcion"].string ?? ""
                    let idUser = boat["usuarioId"].int ?? 0
                    
                    let user = boat["usuario"]
                    let userId = user["id"].int ?? 0
                    let email = user["correo"].string ?? ""
                    let nameUser = user["nombre"].string ?? ""
                    let lastName = user["apellido"].string ?? ""
                    let rfc = user["rfc"].string ?? ""
                    let roleName = user["rol"].string ?? ""
                    let role = Role(id: 0, name: roleName)
                    let userInfo = User(id: userId, email: email, name: nameUser, lastName: lastName, password: "", rfc: rfc, role: role)
                    
                    
                    let boat = Boat(id: id, name: name, description: description, userId: idUser, user: userInfo)
                    boatsInfo.append(boat)
                }
                
                completionHandler(true, boatsInfo)
            case .failure(let error):
                print(error)
                completionHandler(false, [])
            }
        }
    }
    
    static func editBoat(idBoat : Int, boat : Boat, completionHandler:@escaping (_ status : Bool, _ message : String)->()){
        //Alamofire Put Request
        let parameters : Parameters = [
            "nombre" : boat.name,
            "descripcion" : boat.description,
            "usuarioId" : boat.userId
        ]
        
        Alamofire.request("\(WebLinks.Service.urlBoats)\(idBoat)", method: .put, parameters: parameters, encoding: JSONEncoding.default, headers: WebLinks.headers).responseJSON { (response:DataResponse<Any>) in
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
    
    static func deleteBoat(idBoat: Int, completionHandler :@escaping (_ succes: Bool, _ message: String)->()){
        Alamofire.request("\(WebLinks.Service.urlBoats)\(idBoat)", method: .delete, parameters: nil, encoding: JSONEncoding.default, headers: WebLinks.headers).responseJSON {
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
    
    
    
    
    
}
