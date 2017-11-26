//
//  WebServiceUser.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 06/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import Foundation
import Alamofire
import SwiftyJSON

struct WebServiceUser {
    static func getTokenUser(email : String, password:  String, completionHandler :@escaping (_ status: Bool, _ message: String)->()) {
        let parameters : Parameters = [
            "grant_type" : "password",
            "userName" : email,
            "password" : password
        ]
        
        Alamofire.request(WebLinks.Service.urlGetToken, method: .post, parameters: parameters, encoding: URLEncoding.httpBody).responseJSON { response in
            switch(response.result)
            {
                                
            case .success( _):
                let JSON = response.result.value as! NSDictionary
                if let httpStatusCode = response.response?.statusCode
                {
                    var message = ""
                    switch(httpStatusCode){
                    case 200:
                        if let token = JSON["access_token"] {
                            WebLinks.headers["Authorization"] = "bearer \(token)"
                            dataGlobal.set(token, forKey: DataGlobal.keyToken)
                            dataGlobal.synchronize()
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
    
    static func recoverPassword(email : String, completionHandler :@escaping (_ status: Bool, _ message: String)->()) {
        let parameters : Parameters = [
            "mailTo" : email
        ]
        
        Alamofire.request(WebLinks.Service.urlRecoverPassword, method: .post, parameters: parameters, encoding: URLEncoding.httpBody).response { response in
            if let httpStatusCode = response.response?.statusCode {
                if httpStatusCode == 200 {
                    completionHandler(true, "")
                }else {
                    completionHandler(false, "Ha ocurrido un error (\(httpStatusCode)), por favor intente mas tarde!")
                    print("Error \(httpStatusCode)")
                }
            }
        }
    }
    
    
    static func createUser(user : User, completionHandler :@escaping (_ status: Bool, _ message: String)->()) {
        
        let parameters : Parameters = [
            "nombre" : user.name,
            "apellido" : user.lastName,
            "correo" : user.email,
            "rfc" : user.rfc,
            "rol" : user.role.name
        ]
        
        Alamofire.request(WebLinks.Service.urlCreateUser, method: .post, parameters: parameters, encoding: URLEncoding.httpBody).responseJSON { response in
            switch(response.result)
            {
                
            case .success( _):
                let JSON = response.result.value as! NSDictionary
                
                if let httpStatusCode = response.response?.statusCode
                {
                    var message = ""
                    switch(httpStatusCode){
                    case 201:
                        if let _ = JSON["id"] {
                            completionHandler(true, message)
                        }
                        
                    default:
                        print("Code: \(httpStatusCode) Mensaje: \(JSON["message"] ?? "N/A")")
                        if let messageResponse = JSON["message"] {
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
    
    static func getUsers(completionHandler:@escaping (_ status : Bool,_ users : [User])->()){
        //Alamofire Get Request
        Alamofire.request(WebLinks.Service.urlUser, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: WebLinks.headers).responseJSON { (response:DataResponse<Any>) in
            switch response.result {
            case .success(let value):
                let jsonResponse = JSON(value)
                let usersArray = jsonResponse.arrayValue
                var usersInfo : [User] = []
                for user in usersArray {
                    let id = user["id"].int ?? 0
                    let email = user["correo"].string ?? ""
                    let name = user["nombre"].string ?? ""
                    let lastName = user["apellido"].string ?? ""
                    let rfc = user["rfc"].string ?? ""
                    let roleName = user["rol"].string ?? ""
                    let role = Role(id: 0, name: roleName)
                    let user = User(id: id, email: email, name: name, lastName: lastName, password: "", rfc: rfc, role: role)
                    usersInfo.append(user)
                }

                completionHandler(true,usersInfo)
            case .failure(let error):
                print(error)
                completionHandler(false, [])
            }
        }
    }
    
    static func getFishers(completionHandler:@escaping (_ status : Bool,_ users : [User])->()){
        //Alamofire Get Request
        Alamofire.request(WebLinks.Service.urlFishers, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: WebLinks.headers).responseJSON { (response:DataResponse<Any>) in
            switch response.result {
            case .success(let value):
                let jsonResponse = JSON(value)
                let usersArray = jsonResponse.arrayValue
                var usersInfo : [User] = []
                for user in usersArray {
                    let id = user["id"].int ?? 0
                    let email = user["correo"].string ?? ""
                    let name = user["nombre"].string ?? ""
                    let lastName = user["apellido"].string ?? ""
                    let rfc = user["rfc"].string ?? ""
                    let roleName = user["rol"].string ?? ""
                    let role = Role(id: 0, name: roleName)
                    let user = User(id: id, email: email, name: name, lastName: lastName, password: "", rfc: rfc, role: role)
                    usersInfo.append(user)
                }
                
                completionHandler(true,usersInfo)
            case .failure(let error):
                print(error)
                completionHandler(false, [])
            }
        }
    }
    
    
    static func editUser(idUser : Int, user : User, completionHandler:@escaping (_ status : Bool, _ message : String)->()){
        //Alamofire Put Request
        let parameters : Parameters = [
            "nombre" : user.name,
            "apellido" : user.lastName,
            "correo" : user.email,
            "rfc" : user.rfc,
            "rol" : user.role.name
        ]
        
        Alamofire.request("\(WebLinks.Service.urlUser)\(idUser)", method: .put, parameters: parameters, encoding: JSONEncoding.default, headers: WebLinks.headers).responseJSON { (response:DataResponse<Any>) in
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
    
    static func deleteUser(idUser: Int, completionHandler :@escaping (_ succes: Bool, _ message: String)->()){
        Alamofire.request("\(WebLinks.Service.urlUser)\(idUser)", method: .delete, parameters: nil, encoding: JSONEncoding.default, headers: WebLinks.headers).responseJSON{
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
