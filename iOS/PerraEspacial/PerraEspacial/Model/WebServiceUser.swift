//
//  WebServiceUser.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 06/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import Foundation
import Alamofire

struct WebServiceUser {
    static func getTokenUser(name : String, password:  String, completionHandler :@escaping (_ status: Bool, _ message: String)->()) {
        let parameters : Parameters = [
            "grant_type" : "password",
            "userName" : name,
            "password" : password
        ]
        
        Alamofire.request(WebLinks.Service.urlGetToken, method: .post, parameters: parameters, encoding: URLEncoding.httpBody).responseJSON { response in
            switch(response.result)
            {
                
                
            case .success( _):
                let JSON = response.result.value as! NSDictionary
                
                print("PARAMETERS: \(parameters)")
                print("JSON: \(JSON)")
                
                if let httpStatusCode = response.response?.statusCode
                {
                    var status = false
                    var message = ""
                    switch(httpStatusCode){
                    case 201:
                        if let token = JSON["token"] {
                            WebLinks.headers["Authorization"] = "bearer \(token)"
                            dataGlobal.set(token, forKey: DataGlobal.keyToken)
                            dataGlobal.synchronize()
                            status = true
                        }
                        
                    default:
                        print("Code: \(httpStatusCode) Mensaje: \(JSON["message"] ?? "N/A")")
                        if let messageResponse = JSON["message"] {
                            message = messageResponse as! String
                        }
                    }
                    completionHandler(status, message)
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
}
