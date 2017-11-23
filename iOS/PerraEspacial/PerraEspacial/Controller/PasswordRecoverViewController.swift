//
//  PasswordRecoverViewController.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 05/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

class PasswordRecoverViewController: UIViewController {
    //MARK: - IBOutlets
    @IBOutlet weak var textFieldEmail: UITextField!
    
    
    //MARK: - View Life
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    //MARK: - Actions
    @IBAction func buttonRecoverPressed(_ sender: Any) {
        if textFieldEmail.text != "" {
            if isValidEmailAddress(emailAddressString: textFieldEmail.text!) {
                WebServiceUser.recoverPassword(email: textFieldEmail.text!, completionHandler: { (status : Bool, message : String) in
                    if status {
                        self.alert(title: "Listo", message: "Se ha enviado un mensaje a \(self.textFieldEmail.text ?? "")")
                        self.textFieldEmail.text = ""
                    }else {
                        self.alert(title: "Error", message: message)
                    }
                })
            }else {
                self.alert(title: "Correo no valido", message: "Favor de ingresar un correo valido. \n Ejemplo: Correo@ejemplo.com")
            }
        }else {
            self.alert(title: "Requerido", message: "Favor de ingresar su correo")
        }
    }
    
    //MARK: - Functions
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
