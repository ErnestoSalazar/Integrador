//
//  AddBoatViewController.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 17/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

class AddBoatViewController: UIViewController, UITextFieldDelegate, UIPickerViewDelegate, UIPickerViewDataSource {
    //MARK: - IBOutlets
    @IBOutlet weak var textFieldName: UITextField!
    @IBOutlet weak var textFieldFisher: UITextField!
    @IBOutlet weak var textViewDescription: UITextView!
    @IBOutlet weak var buttonAdd: UIButton!
    
    //MARK: - Varailabels And Constants
    let picker = UIPickerView()
    let fishers = ["Pescador 1", "Pescador 2", "Pescador 3", "Pescador 4", "Pescador 5"]
    var indexToEditBoat = 0
    
    //MARK: - View Life
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setDelegates()
        self.setPicker()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        verifyIfIsEditing()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        dataGlobal.removeObject(forKey: DataGlobal.keyIndexToEditBoat)
        dataGlobal.synchronize()
    }
    
    //MARK: - Actions
    @IBAction func createButtonPressed(_ sender: Any) {
        if self.verifyInputs() {
            if isEditing {
                self.editBoat()
            }else {
                self.addBoat()
            }
        }
    }
    
    
    
    //MARK: - PickerView Delegate And DataSource
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return fishers.count
    }
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return fishers[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        textFieldFisher.text = fishers[row]
    }
    
    
    //MARK: - Functions
    func setDelegates(){
        self.textFieldFisher.delegate = self
        self.picker.delegate = self
        self.picker.dataSource = self
    }
    
    func verifyInputs() -> Bool {
        if textFieldName.text == "" {
            self.alert(title: "Valor requerido", message: "Favor de ingresar el nombre del barco")
            return false
        }else if textFieldFisher.text == "" {
            self.alert(title: "Valor requerido", message: "Favor de seleccionar un pescador")
            return false
        }else if textViewDescription.text == "" {
            self.alert(title: "Valor requerido", message: "Favor de añadir una descripción")
            return false
        }
        return true
    }
    
    func setPicker(){
        // ToolBar
        let toolBar = UIToolbar()
        toolBar.barStyle = .black
        toolBar.tintColor = UIColor.white
        toolBar.sizeToFit()
        
        // Adding Button ToolBar
        let doneButton = UIBarButtonItem(title: "Done", style: .done, target: self, action: #selector(doneClick))
        toolBar.setItems([doneButton], animated: false)
        toolBar.isUserInteractionEnabled = true
        
        //Binding to textfield
        textFieldFisher.inputView = picker
        textFieldFisher.inputAccessoryView = toolBar
        textFieldName.inputAccessoryView = toolBar
        textViewDescription.inputAccessoryView = toolBar
    }
    
    @objc func doneClick() {
        self.view.endEditing(true)
    }
    
    func alert(title: String , message: String) {
        let alertController = UIAlertController(title: title, message: message, preferredStyle: UIAlertControllerStyle.alert)
        let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default) {
            (result : UIAlertAction) -> Void in
        }
        alertController.addAction(okAction)
        self.present(alertController, animated: true, completion: nil)
    }
    
    func createBoatObject() -> Boat {
        let boat = Boat(id: 0, name: textFieldName.text!, description: textViewDescription.text!, userId: 1)
        return boat
    }
    
    func verifyIfIsEditing(){
        if let index = dataGlobal.value(forKey: DataGlobal.keyIndexToEditBoat) as? Int {
            self.indexToEditBoat = index
            self.isEditing = true
            self.setBoatsValuesToEdit()
        }
    }
    
    func addBoat(){
        let boat = self.createBoatObject()
        WebServiceBoat.createBoat(boat: boat, completionHandler: { (status : Bool, message : String) in
            if status {
                self.navigationController?.popViewController(animated: true)
            }else {
                self.alert(title: "Error", message: message)
            }
        })
    }
    
    func editBoat(){
        let boat = self.createBoatObject()
        let idBoat = boats[self.indexToEditBoat].id
        WebServiceBoat.editBoat(idBoat: idBoat, boat: boat) { (status : Bool, message : String) in
            if status {
                self.navigationController?.popViewController(animated: true)
            }else {
                self.alert(title: "Error", message: message)
            }
        }
    }
    
    func setBoatsValuesToEdit() {
        self.title = "Editar Barco"
        self.buttonAdd.setTitle("EDITAR", for: .normal)
        let boat = boats[self.indexToEditBoat]
        self.textFieldName.text = boat.name
        self.textFieldFisher.text = ""
        self.textViewDescription.text = boat.description
    }

}
