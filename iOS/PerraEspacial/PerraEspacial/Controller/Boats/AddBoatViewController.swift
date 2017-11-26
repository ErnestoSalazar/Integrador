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
    var fishers : [User] = []
    var indexToEditBoat = 0
    var selectedFisher = 0
    
    //MARK: - View Life
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setDelegates()
        self.setPicker()
        self.getFishers()
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
        self.view.makeToastActivity(.center)
        if self.verifyInputs() {
            if isEditing {
                self.editBoat()
            }else {
                self.addBoat()
            }
        }else {
            self.view.hideToastActivity()
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
        return fishers[row].name
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        self.textFieldFisher.text = fishers[row].name
        self.selectedFisher = row
    }
    
    
    
    
    //MARK: - Functions
    func setDelegates(){
        self.textFieldFisher.delegate = self
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
        textFieldFisher.inputView = picker
        textFieldFisher.inputAccessoryView = toolBar
        textFieldName.inputAccessoryView = toolBar
        textViewDescription.inputAccessoryView = toolBar
    }
    
    @objc func doneClick() {
        self.view.endEditing(true)
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
    
    func alert(title: String , message: String) {
        let alertController = UIAlertController(title: title, message: message, preferredStyle: UIAlertControllerStyle.alert)
        let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default) {
            (result : UIAlertAction) -> Void in
        }
        alertController.addAction(okAction)
        self.present(alertController, animated: true, completion: nil)
    }
    
    func createBoatObject() -> Boat {
        let userId = fishers[self.selectedFisher].id
        let user = fishers[self.selectedFisher]
        let boat = Boat(id: 0, name: textFieldName.text!, description: textViewDescription.text!, userId: userId, user: user)
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
                self.view.hideToastActivity()
                self.navigationController?.popViewController(animated: true)
            }else {
                self.view.hideToastActivity()
                self.alert(title: "Error", message: message)
            }
        })
    }
    
    func editBoat(){
        let boat = self.createBoatObject()
        let idBoat = boats[self.indexToEditBoat].id
        WebServiceBoat.editBoat(idBoat: idBoat, boat: boat) { (status : Bool, message : String) in
            if status {
                self.view.hideToastActivity()
                self.navigationController?.popViewController(animated: true)
            }else {
                self.view.hideToastActivity()
                self.alert(title: "Error", message: message)
            }
        }
    }
    
    func setBoatsValuesToEdit() {
        self.title = "Editar Barco"
        self.buttonAdd.setTitle("EDITAR", for: .normal)
        let boat = boats[self.indexToEditBoat]
        self.textFieldName.text = boat.name
        self.textViewDescription.text = boat.description
        self.setSelectedFisherToEdit(idUser: boat.user?.id ?? 0)
    }
    
    func setSelectedFisherToEdit(idUser : Int){
        for i in 0...fishers.count-1 {
            if idUser == fishers[i].id {
                self.textFieldFisher.text = fishers[i].name
                self.picker.selectRow(i, inComponent: 0, animated: true)
                break;
            }
        }
    }
    
    func getFishers(){
        self.view.makeToastActivity(.center)
        WebServiceUser.getFishers { (status : Bool, fishersArray : [User]) in
            if status && fishersArray.count > 0 {
                self.fishers = fishersArray
                self.picker.reloadAllComponents()
                self.view.hideToastActivity()
            }else {
                self.view.hideToastActivity()
            }
        }
    }

}
