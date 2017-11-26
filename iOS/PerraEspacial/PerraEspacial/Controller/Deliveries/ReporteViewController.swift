//
//  ReporteViewController.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 13/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

class ReporteViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, UITextFieldDelegate {
    //MARK: - IBOutlets
    @IBOutlet weak var textFieldDateBegin: UITextField!
    @IBOutlet weak var textFieldDateEnd: UITextField!
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var buttonSearch: UIButton!
    
    //MARK: - Varailabels And Constants
    let datePicker = UIDatePicker()
    let dateFormatter = DateFormatter()
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
    
    
    //MARK: - Tableview Delegate And DataSource
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return totalRows
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if reports.count > 0 {
            let cell = Bundle.main.loadNibNamed("ReporteTableViewCell", owner: self, options: nil)?.first as! ReporteTableViewCell
            let report = reports[indexPath.row]
                cell.labelFolio.text = report.folio
                cell.labelBacona.text = "\(report.totalBocona)"
                cell.labelCrinuda.text = "\(report.totalCrinuda)"
                cell.labelJaponesa.text = "\(report.totalJaponesa)"
                cell.labelMacarela.text = "\(report.totalMacarela)"
                cell.labelAnchoveta.text = "\(report.totalAnchoveta)"
                cell.labelMonterrey.text = "\(report.totalMonterrey)"
                cell.labelTotal.text = "\(report.totals)"
            return cell
        }else {
            let cell = Bundle.main.loadNibNamed("NotFoundTableViewCell", owner: self, options: nil)?.first as! NotFoundTableViewCell
            return cell
        }
    }
    
    //MARK: - Actions
    @IBAction func buttonSearchPressed(_ sender: Any) {
        if textFieldDateBegin.text == "" || textFieldDateEnd.text == "" {
            self.alert(title: "Requerido", message: "Es necesario ingresar una fecha de inicio y una de cierre")
        }else {
            self.getReports()
        }
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
    
    func getReports(){
        WebServiceReport.getReport(dateBegin: self.dateBegin, dateEnd: self.dateEnd) { (status : Bool, reportsArray : [Report]) in
            if status {
                reports = reportsArray
                self.totalRows = reports.count
                self.tableView.reloadData()
            }else {
                self.totalRows = 1
                self.tableView.reloadData()
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
