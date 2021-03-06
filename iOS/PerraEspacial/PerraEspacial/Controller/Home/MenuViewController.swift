//
//  MenuViewController.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 15/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

class MenuViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    //MARK: - IBOutlets
    @IBOutlet weak var tableView: UITableView!
    
    
    //MARK: - Varailabels And Constants
    let menuTitles = ["Reportes","Usuarios","Barcos","Entradas","Cerrar Sesión"]
    let menuIcons = [#imageLiteral(resourceName: "reportes"), #imageLiteral(resourceName: "usuarios"), #imageLiteral(resourceName: "barcos"), #imageLiteral(resourceName: "entregas"), #imageLiteral(resourceName: "logout")]
    let segueToUsers = "segueToUsers"
    let segueToDeliveries = "segueToDeliveries"
    let segueToBoats = "segueToBoats"
    let segueToReports = "segueToReports"
    let segueToLogin = "segueToLogin"
    
    //MARK: - View Life
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setDelegates()
    }
    
    //MARK: TableView Delegate And DataSource
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if isAdmin {
            return menuTitles.count
        }else {
            return 2
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if isAdmin {
            let cell = Bundle.main.loadNibNamed("MenuTableViewCell", owner: self, options: nil)?.first as! MenuTableViewCell
            cell.labelTitle.text = menuTitles[indexPath.row]
            cell.imageIcon.image = menuIcons[indexPath.row]
            return cell
        }else {
            let cell = Bundle.main.loadNibNamed("MenuTableViewCell", owner: self, options: nil)?.first as! MenuTableViewCell
            if indexPath.row == 0 {
                cell.labelTitle.text = "Entradas"
                cell.imageIcon.image = #imageLiteral(resourceName: "entregas")
            }else {
                cell.labelTitle.text = "Cerrar Sesión"
                cell.imageIcon.image = #imageLiteral(resourceName: "logout")
            }
            
            return cell
        }
    }

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if isAdmin {
            if indexPath.row == 0 {
                self.performSegue(withIdentifier: self.segueToReports, sender: self)
            }
            if indexPath.row == 1 {
                self.performSegue(withIdentifier: self.segueToUsers, sender: self)
            }else if indexPath.row == 2 {
                self.performSegue(withIdentifier: self.segueToBoats, sender: self)
            }else if indexPath.row == 3 {
                self.performSegue(withIdentifier: self.segueToDeliveries, sender: self)
            }else {
                DataGlobal.cleanSavedValues()
                self.performSegue(withIdentifier: self.segueToLogin, sender: self)
            }
        }else {
            if indexPath.row == 0 {
                self.performSegue(withIdentifier: self.segueToDeliveries, sender: self)
            }else {
                DataGlobal.cleanSavedValues()
                self.performSegue(withIdentifier: self.segueToLogin, sender: self)
            }
        }
    }
    
    //MARK: - Functions
    func setDelegates(){
        self.tableView.delegate = self
        self.tableView.dataSource = self
    }
}
