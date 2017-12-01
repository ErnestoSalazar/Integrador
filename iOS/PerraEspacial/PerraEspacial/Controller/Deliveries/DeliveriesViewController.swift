//
//  DeliveriesViewController.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 15/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

class DeliveriesViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, UITextFieldDelegate, DeliveriesCellDelegate {
    
    //MARK: - IBOutlets
    @IBOutlet weak var textFieldDateBegin: UITextField!
    @IBOutlet weak var textFieldDateEnd: UITextField!
    @IBOutlet weak var tableView: UITableView!
    
    
    //MARK: - Varailabels And Constants    
    let datePicker = UIDatePicker()
    let dateFormatter = DateFormatter()
    let segueToEditDelivery = "segueToEditDelivery"
    var totalRows = 1
    var dateBegin = ""
    var dateEnd = ""
    
    //MARK: - View Life
    override func viewDidLoad() {
        super.viewDidLoad()        
        self.setDelegates()
        self.setDatePicker()
        self.setDateFormat()
    }

    override func viewDidAppear(_ animated: Bool) {
        cargas.removeAll()
        self.getDeliveries()
    }
    
    //MARK: TableView Delegate And DataSource
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return totalRows
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if deliveries.count > 0 {
            let cell = Bundle.main.loadNibNamed("DeliveriesTableViewCell", owner: self, options: nil)?.first as! DeliveriesTableViewCell
            let delivery = deliveries[indexPath.row]
                cell.labelFolio.text = delivery.folio
                cell.labelDate.text = delivery.date
                cell.labelTime.text = delivery.time
                cell.labelWorkShift.text = delivery.turn
                cell.labelGeneratedBy.text = "\(delivery.user.name) \(delivery.user.lastName)"
            cell.delegate = self
            return cell
        }else {
            let cell = Bundle.main.loadNibNamed("NotFoundTableViewCell", owner: self, options: nil)?.first as! NotFoundTableViewCell
            
            return cell
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
    }
    
    //MARK: - Cell Delegates
    func editDelivery(cell: DeliveriesTableViewCell) {
        if let index = self.tableView.indexPath(for: cell)?.row {
            dataGlobal.set(index, forKey: DataGlobal.keyIndexToEditDelivery)
            dataGlobal.synchronize()
            self.performSegue(withIdentifier: self.segueToEditDelivery, sender: self)
        }
    }
    
    func deleteDelivery(cell: DeliveriesTableViewCell) {
        if let index = self.tableView.indexPath(for: cell)?.row {
            self.deleteDeliveryAlert(index: index)
        }
    }
    
    
    
    //MARK: - Actions
    @IBAction func buttonSearchPressed(_ sender: Any) {
        deliveries.removeAll()
        self.getDeliveries()
    }
    
    
    //MARK: - Functions
    func setDelegates(){
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.textFieldDateBegin.delegate = self
        self.textFieldDateEnd.delegate = self
    }
    
    func setDatePicker(){
        let toolBar = UIToolbar()
        toolBar.barStyle = .black
        toolBar.tintColor = UIColor.white
        toolBar.sizeToFit()
        
        // Adding Button ToolBar
        let doneButton = UIBarButtonItem(title: "Done", style: .done, target: self, action: #selector(doneClick))
        let spaceButton = UIBarButtonItem(barButtonSystemItem: .flexibleSpace, target: nil, action: nil)
        let cancelButton = UIBarButtonItem(title: "Cancel", style: .done, target: self, action: #selector(cancelClick))
        toolBar.setItems([cancelButton, spaceButton, doneButton], animated: false)
        toolBar.isUserInteractionEnabled = true
        
        datePicker.datePickerMode = .date
        textFieldDateBegin.inputAccessoryView = toolBar
        textFieldDateBegin.inputView = datePicker
        textFieldDateEnd.inputAccessoryView = toolBar
        textFieldDateEnd.inputView = datePicker
    }
    
    func setDateFormat(){
        dateFormatter.dateStyle = .short
        dateFormatter.timeStyle = .none
        dateFormatter.dateFormat = "yyyy-MM-dd"
    }
    
    @objc func doneClick(){
        if textFieldDateBegin.isEditing {
            textFieldDateBegin.text = "\(dateFormatter.string(from: datePicker.date))"
            self.dateBegin = "\(dateFormatter.string(from: datePicker.date))"
        }else if textFieldDateEnd.isEditing {
            textFieldDateEnd.text = "\(dateFormatter.string(from: datePicker.date))"
            self.dateEnd = "\(dateFormatter.string(from: datePicker.date))"
        }
        self.view.endEditing(true)
    }
    
    
    @objc func cancelClick(){
        if textFieldDateBegin.isEditing {
            textFieldDateBegin.text = ""
            dateBegin = ""
        }else if textFieldDateEnd.isEditing {
            textFieldDateEnd.text = ""
            dateEnd = ""
        }
        self.view.endEditing(true)
    }

    func getDeliveries(){
        self.view.makeToastActivity(.center)
        WebServiceDeliveries.getDeliveries(dateBegin: self.dateBegin, dateEnd: self.dateEnd) { (status : Bool, deliveriesArray : [Delivery]) in
            if status && deliveriesArray.count > 0 {
                deliveries = deliveriesArray
                self.totalRows = deliveries.count
                self.tableView.reloadData()
                self.view.hideToastActivity()
            }else {
                self.totalRows = 1
                self.tableView.reloadData()
                self.view.hideToastActivity()
            }
        }
    }

    func deleteDeliveryAlert(index : Int){
        let alertController = UIAlertController(title: "Eliminar entrega", message: "¿Está seguro de que desea eliminar la entrega \(deliveries[index].folio)?", preferredStyle: UIAlertControllerStyle.alert)
        
        let deleteAction = UIAlertAction(title: "Eliminar", style: UIAlertActionStyle.destructive) {
            (result : UIAlertAction) -> Void in
            self.deleteDelivery(index: index)
        }
        
        let cancelAction = UIAlertAction(title: "Cancelar", style: UIAlertActionStyle.cancel) {
            (result : UIAlertAction) -> Void in
            print("Cancelar")
        }
        
        alertController.addAction(deleteAction)
        alertController.addAction(cancelAction)
        self.present(alertController, animated: true, completion: nil)
    }
    
    func deleteDelivery(index : Int){
        self.view.makeToastActivity(.center)
        let idDelivery = deliveries[index].id
        WebServiceDeliveries.deleteDelivery(idDelivery: idDelivery) { (status : Bool, message : String) in
            if status {
                deliveries.remove(at: index)
                self.totalRows = deliveries.count
                self.tableView.reloadData()
                self.view.hideToastActivity()
                self.alert(title: "Listo", message: "Entrega Eliminada")
            }else {
                self.view.hideToastActivity()
                self.alert(title: "Error", message: message)
            }
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
    
    
}
