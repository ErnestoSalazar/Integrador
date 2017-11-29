//
//  AddDeliveryViewController.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 26/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

class AddDeliveryViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, CargaCellDelegate {
    //MARK: - IBOutlets
    @IBOutlet weak var tableView: UITableView!
    
    //MARK: - Varailabels And Constants
    var totalRows = 1
    let segueToEditCarga = "segueToEditCarga"
    
    //MARK: - View Life
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setDelegates()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        if cargas.count > 0 {
            self.totalRows = cargas.count
        }else {
            self.totalRows = 1
        }
        self.tableView.reloadData()
    }
    
    //MARK: - TableView Delegate And DataSource
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return totalRows
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if cargas.count > 0 {
            let cell = Bundle.main.loadNibNamed("CargaTableViewCell", owner: self, options: nil)?.first as! CargaTableViewCell
            let carga = cargas[indexPath.row]
                cell.labelBoat.text = carga.boat.name
                cell.labelEspecie.text = carga.especie
                cell.labelSize.text = "\(carga.size)"
                cell.labelTemperature.text = "\(carga.temperature)"
                cell.labelCondition.text = carga.condition
            cell.delegate = self
            return cell
        }else {
            let cell = Bundle.main.loadNibNamed("NotFoundTableViewCell", owner: self, options: nil)?.first as! NotFoundTableViewCell
            cell.labelNotFound.text = "No se han registrado cargas para esta entrega."
            return cell
        }
        
    }
    
    //MARK: - Cell Delegates
    func editCarga(cell: CargaTableViewCell) {
        if let index = self.tableView.indexPath(for: cell)?.row {
            dataGlobal.set(index, forKey: DataGlobal.keyIndexToEditCarga)
            dataGlobal.synchronize()
            self.performSegue(withIdentifier: self.segueToEditCarga, sender: self)
        }
    }
    
    func deleteCarga(cell: CargaTableViewCell) {
        if let index = self.tableView.indexPath(for: cell)?.row {
            self.deleteCargaAlert(index: index)
        }
    }
    
    //MARK: - Actions
    @IBAction func createDeliveryButtonPressed(_ sender: Any) {
        self.view.makeToastActivity(.center)
        if cargas.count > 0 {
            self.createCargas()
        }else {
            self.view.hideToastActivity()
            self.alert(title: "Valores Requeridos", message: "Es necesario agregar al menos una carga")
        }
    }
        
    //MARK: - Functions
    func setDelegates(){
        self.tableView.delegate = self
        self.tableView.dataSource = self
    }
    
    func deleteCargaAlert(index : Int){
        let alertController = UIAlertController(title: "Eliminar Carga", message: "¿Está seguro de que desea eliminar la carga de \(cargas[index].especie)?", preferredStyle: UIAlertControllerStyle.alert)
        
        let deleteAction = UIAlertAction(title: "Eliminar", style: UIAlertActionStyle.destructive) {
            (result : UIAlertAction) -> Void in
            self.deleteCarga(index: index)
        }
        
        let cancelAction = UIAlertAction(title: "Cancelar", style: UIAlertActionStyle.cancel) {
            (result : UIAlertAction) -> Void in
            print("Cancelar")
        }
        
        alertController.addAction(deleteAction)
        alertController.addAction(cancelAction)
        self.present(alertController, animated: true, completion: nil)
    }
    
    func deleteCarga(index : Int){
        let carga = cargas[index]
        if carga.id != 0 {
            WebServiceDeliveries.deleteCarga(idCarga: carga.id, completionHandler: { (status : Bool, message : String) in
                if status {
                    cargas.remove(at: index)
                    if cargas.count > 0 {
                        self.totalRows = cargas.count
                    }else {
                        self.totalRows = 1
                    }
                    self.alert(title: "Listo", message: "Carga eliminada")
                    self.tableView.reloadData()
                }else {
                    self.alert(title: "Error", message: message)
                }
            })
        }else {
            cargas.remove(at: index)
            if cargas.count > 0 {
                self.totalRows = cargas.count
            }else {
                self.totalRows = 1
            }
            self.alert(title: "Listo", message: "Carga eliminada")
            self.tableView.reloadData()
        }
    }
    
    func alert(title: String , message: String) {
        let alertController = UIAlertController(title: title, message: message, preferredStyle: UIAlertControllerStyle.alert)
        let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default) {
            (result : UIAlertAction) -> Void in
        }
        alertController.addAction(okAction)
        self.present(alertController, animated: true, completion: nil)
    }
    
    func createCargas(){
        var idCargas : [Int] = []
        for i in 0...cargas.count-1 {
            WebServiceDeliveries.createCarga(carga: cargas[i], completionHandler: { (idCarga : Int, status : Bool, message : String) in
                if status && idCarga != 0 {
                    idCargas.append(idCarga)
                    if i == cargas.count-1 {
                        self.createDelivery(idCargas: idCargas)
                    }
                }else {
                    self.view.hideToastActivity()
                    self.alert(title: "Error", message: message)
                }
            })
        }
    }
    
    
    func createDelivery(idCargas : [Int]){
        WebServiceDeliveries.createDelivery(idCargas: idCargas) { (status : Bool, message : String) in
            if status {
                self.view.hideToastActivity()
                self.navigationController?.popViewController(animated: true)
            }else {
                self.view.hideToastActivity()
                self.alert(title: "Error", message: message)
            }
        }
    }
    
    
}
