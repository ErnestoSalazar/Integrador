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
    @IBOutlet weak var buttonAdd: UIButton!
    
    //MARK: - Varailabels And Constants
    let picker = UIPickerView()
    let roles = ["Administrador", "Supervisor", "Pescador"]
    var selectedRoleId : Int = 0
    var selectedRoleName : String = ""
    var indexToEditUser = 0
    var isEditingUser : Bool = false
    
    //MARK: - View Life
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setDelegates()
        self.setPicker()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        self.verifyIfIsEditing()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        dataGlobal.removeObject(forKey: DataGlobal.keyIndexToEditUser)
        dataGlobal.synchronize()
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
            if isEditing {
                self.editUser()
            }else {
                self.addUser()
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
        return roles.count
    }
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return roles[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        self.selectedRoleId = row
        self.selectedRoleName = roles[row]
        self.textFieldRole.text = roles[row]
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
    
    func addUser(){
        self.view.makeToastActivity(.center)
        let user = self.createUserObject()
        WebServiceUser.createUser(user: user, completionHandler: { (status : Bool, message : String) in
            if status {
                self.view.hideToastActivity()
                self.navigationController?.popViewController(animated: true)
            }else {
                self.view.hideToastActivity()
                self.alert(title: "Error", message: message)
            }
        })

    }
    
    func createUserObject() -> User {
        let role = Role(id: self.selectedRoleId, name: self.selectedRoleName)
        let user = User(id: 0, email: textFieldEmail.text!, name: textFieldName.text!, lastName: textFieldLastName.text!, password: "", rfc: textFieldRfc.text!, role: role)
        return user
    }

    func verifyIfIsEditing(){
        if let index = dataGlobal.value(forKey: DataGlobal.keyIndexToEditUser) as? Int {
            self.indexToEditUser = index
            self.isEditing = true
            self.setUserValuesToEdit()
        }
    }
    
    func editUser(){
        self.view.makeToastActivity(.center)
        let user = self.createUserObject()
        let idUser = users[self.indexToEditUser].id
        WebServiceUser.editUser(idUser : idUser, user: user) { (status : Bool, message : String) in
            if status {
                self.view.hideToastActivity()
                self.navigationController?.popViewController(animated: true)
                self.alert(title: "Listo", message: "Usuario editado correctamente")
            }else {
                self.view.hideToastActivity()
                self.alert(title: "Error", message: message)
            }
        }
    }
    
    func setUserValuesToEdit(){
        self.title = "Editar Usuario"
        self.buttonAdd.setTitle("EDITAR", for: .normal)
        let user = users[self.indexToEditUser]
        self.textFieldName.text = user.name
        self.textFieldLastName.text = user.lastName
        self.textFieldRfc.text = user.rfc
        self.textFieldRole.text = user.role.name
        self.textFieldEmail.text = user.email
        self.selectedRoleId = user.role.id
    }
    
    
}
