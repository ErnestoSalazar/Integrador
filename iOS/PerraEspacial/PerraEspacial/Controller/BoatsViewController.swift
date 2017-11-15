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
    
    
    //MARK: - View Life
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setDelegates()
    }
    
    //MARK: TableView Delegate And DataSource
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 5
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = Bundle.main.loadNibNamed("BoatTableViewCell", owner: self, options: nil)?.first as! BoatTableViewCell
        return cell
    }
    
    //MARK: - Actions
    @IBAction func buttonSearchPressed(_ sender: Any) {
    }
    
    //MARK: - Functions
    func setDelegates(){
        self.tableView.delegate = self
        self.tableView.dataSource = self
    }
}
