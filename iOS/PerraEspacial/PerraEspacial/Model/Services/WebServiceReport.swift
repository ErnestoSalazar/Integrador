//
//  WebServiceReport.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 24/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import Foundation
import Alamofire
import SwiftyJSON

struct WebServiceReport {
    static func getReport(completionHandler:@escaping (_ status : Bool,_ reports : [Report])->()){
        //Alamofire Get Request        
        Alamofire.request("\(WebLinks.Service.urlDeliveries)?fechaInicio=2017/11/13&fechaFin=2017/11/23", method: .get, parameters: nil, encoding: JSONEncoding.default, headers: WebLinks.headers).responseJSON { (response:DataResponse<Any>) in
            switch response.result {
            case .success(let value):
                let jsonResponse = JSON(value)
                let reportsArray = jsonResponse.arrayValue
                var reports : [Report] = []
                for report in reportsArray {
                    let id = report["id"].int ?? 0
                    let folio = report["folop"].string ?? ""
                    let userId = report["usuarioId"].int ?? 0
                    let totals = report["totales"].double ?? 0.0
                    let date = report["fecha"].string ?? ""
                    let time = report["hora"].string ?? ""
                    let turn = report["turno"].string ?? ""
                    let totalMacarela = report["totalMacarela"].double ?? 0
                    let percentMacarela = report["porcentajeMacarela"].double ?? 0
                    let totalRayadillo = report["totalRayadillo"].double ?? 0
                    let percentRayadillo = report["porcentajeRayadillo"].double ?? 0
                    let totalBacona = report["totalBocona"].double ?? 0
                    let percentBacona = report["porcentajeBocona"].double ?? 0
                    let totalAnchoveta = report["totalAnchoveta"].double ?? 0
                    let percentAnchoveta = report["porcentajeAnchoveta"].double ?? 0
                    let totalJaponesa = report["totalJaponesa"].double ?? 0
                    let percentJaponesa = report["porcentajeJaponesa"].double ?? 0
                    let totalMonterrey = report["totalMonterrey"].double ?? 0
                    let percentMonterrey = report["porcentajeMonterrey"].double ?? 0
                    let totalCrinuda = report["totalCrinuda"].double ?? 0
                    let percentCrinuda = report["porcentajeCrinuda"].double ?? 0
                    
                    let cargasArray = report["cargas"].arrayValue
                    var cargas : [Carga] = []
                    for carga in cargasArray {
                        let id = carga["id"].int ?? 0
                        let condition = carga["condicion"].string ?? ""
                        let quantity = carga["cantidad"].double ?? 0.0
                        let temperature = carga["temperatura"].double ?? 0.0
                        let boatId = carga["barcoId"].int ?? 0
                        let especie = carga["especie"].string ?? ""
                        let size = carga["talla"].string ?? ""
                        
                        let cargaInfo = Carga(id: id, condition: condition, quantity: quantity, temperature: temperature, boatId: boatId, especie: especie, size: size)
                        cargas.append(cargaInfo)
                    }
                    
                    let reportInfo = Report(id: id, folio: folio, userId: userId, totals: totals, date: date, turn: turn, time: time, totalMacarela: totalMacarela, percentMacarela: percentMacarela, totalRayadillo: totalRayadillo, percentRayadillo: percentRayadillo, totalBocona: totalBacona, percentBocana: percentBacona, totalAnchoveta: totalAnchoveta, percentAnchoveta: percentAnchoveta, totalJaponesa: totalJaponesa, percentJaponesa: percentJaponesa, totalMonterrey: totalMonterrey, percentMonterrey: percentMonterrey, totalCrinuda: totalCrinuda, percentCrinuda: percentCrinuda, cargas: cargas)
                    reports.append(reportInfo)
                }
                
                
                completionHandler(true, reports)
            case .failure(let error):
                print(error)
                completionHandler(false, [])
            }
        }
    }

}
