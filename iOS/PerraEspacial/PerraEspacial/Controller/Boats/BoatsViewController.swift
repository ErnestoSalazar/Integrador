//
//  BoatsViewController.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 15/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

class BoatsViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    //MARK: - IBOutlets
    @IBOutlet weak var textFieldName: UITextField!
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var buttonSearch: UIButton!
    
    //MARK: - Varailabels And Constants
    var totalRows = 1
    
    //MARK: - View Life
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setDelegates()
    }
    
    //MARK: TableView Delegate And DataSource
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return totalRows
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if boats.count > 0 {
            let cell = Bundle.main.loadNibNamed("BoatTableViewCell", owner: self, options: nil)?.first as! BoatTableViewCell
            let boat = boats[indexPath.row]
                
            
            return cell
        }else {
            let cell = Bundle.main.loadNibNamed("NotFoundTableViewCell", owner: self, options: nil)?.first as! NotFoundTableViewCell
            return cell
        }
    }
    
    //MARK: - Actions
    @IBAction func buttonSearchPressed(_ sender: Any) {
    }
    
    //MARK: - Functions
    func setDelegates(){
        self.tableView.delegate = self
        self.tableView.dataSource = self
    }
    
    func getBoats(){
        WebServiceBoat.getBoats { (status : Bool, boatsArray : [Boat]) in
            if status {
                boats = boatsArray
                self.totalRows = boats.count
                self.tableView.reloadData()
            }else {
                if boats.count <= 0 {
                    self.totalRows = 1
                    self.tableView.reloadData()
                }
            }
        }
    }
    
}
