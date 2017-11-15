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
    
    
    //MARK: - View Life
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setDelegates()
        self.setDatePicker()
    }
    
    
    //MARK: - Tableview Delegate And DataSource
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 5
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = Bundle.main.loadNibNamed("ReporteTableViewCell", owner: self, options: nil)?.first as! ReporteTableViewCell
        return cell
    }
    
    //MARK: - Actions
    @IBAction func buttonSearchPressed(_ sender: Any) {
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
    
    @objc func doneClick(){
        self.view.endEditing(true)
    }
    
    
    @objc func cancelClick(){
        self.view.endEditing(true)
    }
    
    
}
