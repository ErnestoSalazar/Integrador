//
//  Delivery.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 26/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import Foundation

struct Delivery {
    let id : Int
    let folio : String
    let date : String
    let time : String
    let turn : String
    let idUser : Int
    let user : User
    let cargas : [Carga]
}
