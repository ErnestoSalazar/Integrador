//
//  AddCargaViewController.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 26/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

class AddCargaViewController: UIViewController, UITextFieldDelegate, UIPickerViewDelegate, UIPickerViewDataSource {
    //MARK: - IBOutlets
    @IBOutlet weak var textFieldBoat: UITextField!
    @IBOutlet weak var textFieldQuantity: UITextField!
    @IBOutlet weak var textFieldEspecie: UITextField!
    @IBOutlet weak var textFieldSize: UITextField!
    @IBOutlet weak var textFieldTemperature: UITextField!
    @IBOutlet weak var textFieldCondition: UITextField!
    @IBOutlet weak var buttonSave: UIButton!
    
    //MARK: - Varailabels And Constants
    let picker = UIPickerView()
    var viewDidMove : Bool = false
    var isEditingCarga : Bool = false
    var indexToEdit = 0
    var selectedBoatIndex  = -1
    
    //MARK: - View Life
    override func viewDidLoad() {
        super.viewDidLoad()
        self.getBoats()
        self.setDelegates()
        self.setPicker()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        self.verifyIfIsEditing()
    }
    
    //MARK: - TextField Delegate And DataSource
    func textFieldDidBeginEditing(_ textField: UITextField) {
        self.picker.reloadAllComponents()
        
        if textFieldTemperature.isEditing || textFieldCondition.isEditing || textFieldSize.isEditing {
            UIView.animate(withDuration: 0.5, animations: {
                self.view.frame.origin.y = self.view.frame.origin.y-100
                self.viewDidMove = true
            })
        }
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        if viewDidMove {
            UIView.animate(withDuration: 0.5, animations: {
                self.view.frame.origin.y = self.view.frame.origin.y+100
                self.viewDidMove = false
            })
        }
    }

    //MARK: - PickerView Delegate And DataSource
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if textFieldBoat.isEditing {
            return boats.count
        }else if textFieldEspecie.isEditing {
            return especies.count
        }else if textFieldSize.isEditing {
            return sizes.count
        }else if textFieldCondition.isEditing {
            return conditions.count
        }
        return 0
    }
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if textFieldBoat.isEditing {
            self.selectedBoatIndex = row
            return boats[row].name
        }else if textFieldEspecie.isEditing {
            return especies[row]
        }else if textFieldSize.isEditing {
            return sizes[row]
        }else if textFieldCondition.isEditing {
            return conditions[row]
        }
        return ""
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if textFieldBoat.isEditing {
            self.textFieldBoat.text = boats[row].name
        }else if textFieldEspecie.isEditing {
            self.textFieldEspecie.text = especies[row]
        }else if textFieldSize.isEditing {
            self.textFieldSize.text = sizes[row]
        }else if textFieldCondition.isEditing {
            self.textFieldCondition.text = conditions[row]
        }
    }
    
    //MARK: - Actions
    @IBAction func saveButtonPressed(_ sender: Any) {
        if verifyTextFields() {
            let carga = self.createCargaObject()
            if isEditingCarga {
                if carga.id != 0 {
                    self.editCarga(carga: carga)
                }else {
                    cargas[self.indexToEdit] = carga
                }
            }else {
                cargas.append(carga)
            }
            
            self.navigationController?.popViewController(animated: true)
        }
    }
    
    //MARK: - Functions
    func setDelegates(){
        self.textFieldBoat.delegate = self
        self.textFieldEspecie.delegate = self
        self.textFieldSize.delegate = self
        self.textFieldCondition.delegate = self
        self.textFieldTemperature.delegate = self
        self.picker.delegate = self
        self.picker.dataSource = self
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
        self.textFieldBoat.inputView = picker
        self.textFieldEspecie.inputView = picker
        self.textFieldSize.inputView = picker
        self.textFieldCondition.inputView = picker
        
        self.textFieldBoat.inputAccessoryView = toolBar
        self.textFieldEspecie.inputAccessoryView = toolBar
        self.textFieldSize.inputAccessoryView = toolBar
        self.textFieldCondition.inputAccessoryView = toolBar
        self.textFieldQuantity.inputAccessoryView = toolBar
        self.textFieldTemperature.inputAccessoryView = toolBar
    }
    
    @objc func doneClick() {
        self.view.endEditing(true)
    }
    
    func getBoats(){
        self.view.makeToastActivity(.center)
        WebServiceBoat.getBoats { (status : Bool, boatsArray : [Boat]) in
            if status {
                boats = boatsArray
                self.picker.reloadAllComponents()
                self.view.hideToastActivity()
            }else {
                self.view.hideToastActivity()
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
    
    func verifyTextFields() -> Bool {
        if textFieldBoat.text == "" {
            self.alert(title: "Valor requerido", message: "Favor de seleccionar un Barco")
            return false
        }else if textFieldQuantity.text == "" {
            self.alert(title: "Valor requerido", message: "Favor indicar la cantidad en Toneladas")
            return false
        }else if textFieldEspecie.text == "" {
            self.alert(title: "Valor requerido", message: "Favor de seleccionar una Especie")
            return false
        }else if textFieldSize.text == "" {
            self.alert(title: "Valor requerido", message: "Favor de seleccionar una Talla")
            return false
        }else if textFieldTemperature.text == "" {
            self.alert(title: "Valor requerido", message: "Favor indicar la Temperatura")
            return false
        }else if textFieldCondition.text == "" {
            self.alert(title: "Valor requerido", message: "Favor de seleccionar la condición")
            return false
        }
        return true
    }

    func createCargaObject() -> Carga {
        var id = 0
        let condition = textFieldCondition.text!
        let quantity = Double(textFieldQuantity.text!)!
        let temperature = Double(textFieldTemperature.text!)!
        let especie = textFieldEspecie.text!
        let size = textFieldSize.text!
        var boat = boats[0]
        
        if selectedBoatIndex > 0 {
            boat = boats[self.selectedBoatIndex]
        }
        
        if isEditing {
            id = cargas[self.indexToEdit].id
            
            if self.selectedBoatIndex == -1 {
                boat = cargas[self.indexToEdit].boat
            }
            
        }
        
        let carga = Carga(id: id, condition: condition, quantity: quantity, temperature: temperature, boatId: boat.id, boat: boat, especie: especie, size: size)
        return carga
    }
    
    func verifyIfIsEditing(){
        if let index = dataGlobal.value(forKey: DataGlobal.keyIndexToEditCarga) as? Int {
            self.isEditingCarga = true
            self.indexToEdit = index
            setValuesToEdit()
            dataGlobal.removeObject(forKey: DataGlobal.keyIndexToEditCarga)
            dataGlobal.synchronize()
        }
    }
    
    func setValuesToEdit(){
        self.title = "Editar Carga"
        self.buttonSave.setTitle("Editar", for: .normal)
        
        let carga = cargas[indexToEdit]
        self.textFieldBoat.text = carga.boat.name
        self.textFieldQuantity.text = "\(carga.quantity)"
        self.textFieldEspecie.text = carga.especie
        self.textFieldSize.text = carga.size
        self.textFieldTemperature.text = "\(carga.temperature)"
        self.textFieldCondition.text = carga.condition
    }
    
    func editCarga(carga : Carga){
        WebServiceDeliveries.editCarga(idCarga: carga.id, carga: carga) { (status : Bool, message : String) in
            if status {
                cargas[self.indexToEdit] = carga
                self.navigationController?.popViewController(animated: true)
            }else {
                self.alert(title: "Error", message: message)
            }
        }
    }
    
    
}
