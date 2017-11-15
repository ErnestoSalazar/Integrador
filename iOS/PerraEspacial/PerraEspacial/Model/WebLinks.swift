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
    }
    
}
