//
//  Report.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 24/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import Foundation
struct Report {
    let id : Int
    let folio : String
    let userId : Int
    let totals : Double
    let date : String
    let turn : String
    let time : String
    let totalMacarela : Double
    let percentMacarela : Double
    let totalRayadillo : Double
    let percentRayadillo : Double
    let totalBocona : Double
    let percentBocana : Double
    let totalAnchoveta : Double
    let percentAnchoveta : Double
    let totalJaponesa : Double
    let percentJaponesa : Double
    let totalMonterrey : Double
    let percentMonterrey : Double
    let totalCrinuda : Double
    let percentCrinuda : Double
    let cargas : [Carga]
}

