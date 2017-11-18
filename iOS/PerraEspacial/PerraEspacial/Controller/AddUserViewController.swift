//
//  AddUserViewController.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 17/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

class AddUserViewController: UIViewController, UITextFieldDelegate, UIPickerViewDelegate, UIPickerViewDataSource {
    //MARK: - IBOutlets
    @IBOutlet weak var textFieldName: UITextField!
    @IBOutlet weak var textFieldLastName: UITextField!
    @IBOutlet weak var textFieldRfc: UITextField!
    @IBOutlet weak var textFieldRole: UITextField!
    @IBOutlet weak var textFieldEmail: UITextField!
    
    //MARK: - Varailabels And Constants
    let picker = UIPickerView()
    let fishers = ["Administrador", "Pescador"]
    
    
    //MARK: - View Life
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setDelegates()
        self.setPicker()
    }
    
    //MARK: - TextField Delegate
    func textFieldDidBeginEditing(_ textField: UITextField) {
        if textFieldEmail.isEditing {
            UIView.animate(withDuration: 0.5, animations: {
                self.view.frame.origin.y = self.view.frame.origin.y-50
            })
        }
    }
    func textFieldShouldEndEditing(_ textField: UITextField) -> Bool {
        if textFieldEmail.isEditing {
            UIView.animate(withDuration: 0.5, animations: {
                self.view.frame.origin.y = self.view.frame.origin.y+50
            })
        }
        return true
    }
    
    
    //MARK: - Actions
    @IBAction func addButtonPressed(_ sender: Any) {
        if self.verifyInputs() {
            self.navigationController?.popViewController(animated: true)
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
        self.textFieldRole.text = fishers[row]
    }
    
    
    //MARK: - Functions
    func setDelegates(){
        self.textFieldRole.delegate = self
        self.textFieldEmail.delegate = self
        self.picker.delegate = self
        self.picker.dataSource = self
    }
    
    func verifyInputs() -> Bool {
        if textFieldName.text == "" {
            self.alert(title: "Valor requerido", message: "Favor de ingresar el nombre del usuario")
            return false
        }else if textFieldLastName.text == "" {
            self.alert(title: "Valor requerido", message: "Favor de ingresar el apellido del usuario")
            return false
        }else if textFieldRfc.text == "" {
            self.alert(title: "Valor requerido", message: "Favor de ingresar el RFC del usuario")
            return false
        }else if textFieldRole.text == "" {
            self.alert(title: "Valor requerido", message: "Favor de seleccionar el Rol de usuario")
            return false
        }else if textFieldEmail.text == "" {
            self.alert(title: "Valor requerido", message: "Favor de ingresar el correo del usuario")
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
        textFieldRole.inputView = picker
        textFieldName.inputAccessoryView = toolBar
        textFieldLastName.inputAccessoryView = toolBar
        textFieldRfc.inputAccessoryView = toolBar
        textFieldRole.inputAccessoryView = toolBar
        textFieldEmail.inputAccessoryView = toolBar
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

}
