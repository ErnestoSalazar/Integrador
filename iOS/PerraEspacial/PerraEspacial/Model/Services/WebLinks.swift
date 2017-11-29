//
//  WebLinks.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 06/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import Foundation
import Alamofire

struct WebLinks {
    private static let baseUrl: String = "http://spacedog.azurewebsites.net/"
    
    static var headers: HTTPHeaders = [
        "Authorization": "bearer \(dataGlobal.value(forKey: DataGlobal.keyToken) as? String ?? "")"
        
    ]
    
    struct Service {
        static let urlGetToken = baseUrl + "login"
        static let urlRecoverPassword = baseUrl + "login/recover"
        static let urlCreateUser = baseUrl + "api/users"
        static let urlUser = baseUrl + "api/users/"
        static let urlFishers = baseUrl + "api/users?rol=Pescador"
        static let urlBoats = baseUrl + "api/barcos/"
        static let urlDeliveries = baseUrl + "/api/entradas"
        static let urlCargas = baseUrl + "/api/cargas"
        static let urlReports = baseUrl + "/api/entradas/reportes"
    }
    
}
