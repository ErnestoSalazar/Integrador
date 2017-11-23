//
//  DataGlobal.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 06/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import Foundation

let dataGlobal = UserDefaults.standard
var isAdmin : Bool = false
var users : [User] = []
var boats : [Boat] = []

struct DataGlobal {
    static let keyToken = "token"
    static let keyIndexToEditUser = "indexToEditUser"
}
