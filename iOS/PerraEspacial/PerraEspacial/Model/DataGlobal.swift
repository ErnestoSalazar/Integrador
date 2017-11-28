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
var loggedUser : User?
var users : [User] = []
var boats : [Boat] = []
var reports : [Report] = []
var deliveries : [Delivery] = []
var cargas : [Carga] = []
let especies : [String] = ["Macarela", "Japonesa", "Monterrey", "Rayadillo", "Bocona", "Anchoveta", "Crinuda"]
let sizes : [String] = ["S","M","L","XL"]
let conditions : [String] = ["Mala", "Regular", "Buena"]

struct DataGlobal {
    static let keyToken = "token"
    static let keyIndexToEditUser = "indexToEditUser"
    static let keyIndexToEditBoat = "indexToEditBoat"
    static let keyIndexToEditCarga = "indexToEditCarga"
    
    static func cleanSavedValues(){
        let keys = [keyToken, keyIndexToEditUser, keyIndexToEditBoat]
        
        for key in keys {
            dataGlobal.removeObject(forKey: key)
        }
        
        dataGlobal.synchronize()
        
    }
    
}
