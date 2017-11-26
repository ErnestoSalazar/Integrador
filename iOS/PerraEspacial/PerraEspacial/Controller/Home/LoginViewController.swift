//
//  LoginViewController.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 03/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {
    //MARK: - IBOutlets
    @IBOutlet weak var viewBackground: UIView!
    @IBOutlet weak var textFieldEmail: UITextField!
    @IBOutlet weak var textFieldPassword: UITextField!
    @IBOutlet weak var buttonLogin: UIButton!
    
    //MARK: - Varailabels And Constants
    let segueToMenu = "segueToMenu"
    
    //MARK: - View Life
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    //MARK: - Actions
    @IBAction func buttonLoginPressed(_ sender: Any) {
        self.view.makeToastActivity(.center)
        if self.verifyInputs() {
            WebServiceUser.getTokenUser(email: "\(textFieldEmail.text!)", password: "\(textFieldPassword.text!)", completionHandler: { (status : Bool, message : String) in
                if status {
                    self.view.hideToastActivity()
                    self.performSegue(withIdentifier: self.segueToMenu, sender: self)
                }else {
                    self.view.hideToastActivity()
                    self.alert(title: "Error", message: message)
                }
            })
        }else {
            self.view.hideToastActivity()
        }
    }
    
    //MARK: - Functions
    func verifyInputs() -> Bool {
        if textFieldEmail.text == "" {
            self.alert(title: "Requerido", message: "Favor de ingresar su correo")
            return false
        }else if textFieldPassword.text == "" {
            self.alert(title: "Requerido", message: "Favor de ingresar su contraseña")
            return false
        }else if !isValidEmailAddress(emailAddressString: textFieldEmail.text!) {
            self.alert(title: "Correo no valido", message: "Favor de ingresar un correo valido. \n Ejemplo: Correo@ejemplo.com")
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
    
    func isValidEmailAddress(emailAddressString: String) -> Bool {
        var returnValue = true
        let emailRegEx = "[A-Z0-9a-z.-_]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}"
        
        do {
            let regex = try NSRegularExpression(pattern: emailRegEx)
            let nsString = emailAddressString as NSString
            let results = regex.matches(in: emailAddressString, range: NSRange(location: 0, length: nsString.length))
            
            if results.count == 0
            {
                returnValue = false
            }
            
        } catch let error as NSError {
            print("invalid regex: \(error.localizedDescription)")
            returnValue = false
        }
        
        return  returnValue
    }
}
