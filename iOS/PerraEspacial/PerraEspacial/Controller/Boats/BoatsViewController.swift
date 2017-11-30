//
//  BoatsViewController.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 15/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

class BoatsViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, BoatCellDelegate, UISearchBarDelegate {
    //MARK: - IBOutlets
    @IBOutlet weak var tableView: UITableView!
    
    //MARK: - Varailabels And Constants
    var totalRows = 1
    let segueToEditBoat = "segueToEditBoat"
    
    //MARK: - View Life
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setDelegates()
        if boats.count <= 0 {
            self.getBoats()
        }
        self.searchBarSetUp()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        boats.removeAll()
        self.getBoats()
    }
    
    //MARK: - Search Bar Delegate
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        if searchText.isEmpty{
            users.removeAll()
            self.getBoats()
            self.totalRows = boats.count
            self.tableView.reloadData();
        }else {
            filterTableView(text: searchText);
        }
    }
    
    
    func filterTableView(text: String){
        boats = boats.filter { (mod) -> Bool in
            return mod.name.lowercased().contains(text.lowercased());
        }
        self.totalRows = boats.count
        self.tableView.reloadData();
    }
    
    func searchBarSetUp(){
        let searchBar = UISearchBar(frame: CGRect(x: 0, y: 0, width: UIScreen.main.bounds.width, height: 70));
        searchBar.showsScopeBar = true;
        searchBar.placeholder = "Buscar"
        self.navigationItem.titleView = searchBar
        searchBar.delegate = self;
    }
    
    //MARK: TableView Delegate And DataSource
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return totalRows
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if boats.count > 0 {
            let cell = Bundle.main.loadNibNamed("BoatTableViewCell", owner: self, options: nil)?.first as! BoatTableViewCell
            let boat = boats[indexPath.row]
                cell.labelName.text = boat.name
                cell.labelDescription.text = boat.description
                cell.labelUser.text = "\(boat.user?.name ?? "") \(boat.user?.lastName ?? "")"
            cell.delegate = self
            return cell
        }else {
            let cell = Bundle.main.loadNibNamed("NotFoundTableViewCell", owner: self, options: nil)?.first as! NotFoundTableViewCell
            return cell
        }
    }
    
    //MARK: - Cell Delegates
    func editBoat(cell: BoatTableViewCell) {
        if let index = self.tableView.indexPath(for: cell)?.row {
            dataGlobal.set(index, forKey: DataGlobal.keyIndexToEditBoat)
            dataGlobal.synchronize()
            self.performSegue(withIdentifier: self.segueToEditBoat, sender: self)
        }
    }
    
    func deleteBoat(cell: BoatTableViewCell) {
        if let index = self.tableView.indexPath(for: cell)?.row {
         self.deleteBoatAlert(index: index)
        }
    }
    
    //MARK: - Functions
    func setDelegates(){
        self.tableView.delegate = self
        self.tableView.dataSource = self
    }
    
    func alert(title: String , message: String) {
        let alertController = UIAlertController(title: title, message: message, preferredStyle: UIAlertControllerStyle.alert)
        let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default) {
            (result : UIAlertAction) -> Void in
        }
        alertController.addAction(okAction)
        self.present(alertController, animated: true, completion: nil)
    }
    
    func getBoats(){
        self.view.makeToastActivity(.center)
        WebServiceBoat.getBoats { (status : Bool, boatsArray : [Boat]) in
            if status {
                boats = boatsArray
                self.totalRows = boats.count
                self.tableView.reloadData()
                self.view.hideToastActivity()
            }else {
                if boats.count <= 0 {
                    self.totalRows = 1
                    self.tableView.reloadData()
                }
                self.view.hideToastActivity()
            }
        }
    }
    
    func deleteBoatAlert(index : Int){
        let alertController = UIAlertController(title: "Eliminar barco", message: "¿Está seguro de que desea eliminar a \(boats[index].name)?", preferredStyle: UIAlertControllerStyle.alert)
        
        let deleteAction = UIAlertAction(title: "Eliminar", style: UIAlertActionStyle.destructive) {
            (result : UIAlertAction) -> Void in
            self.deleteBoat(index: index)
        }
        
        let cancelAction = UIAlertAction(title: "Cancelar", style: UIAlertActionStyle.cancel) {
            (result : UIAlertAction) -> Void in
            print("Cancelar")
        }
        
        alertController.addAction(deleteAction)
        alertController.addAction(cancelAction)
        self.present(alertController, animated: true, completion: nil)
    }
    
    func deleteBoat(index : Int) {
        self.view.makeToastActivity(.center)
        WebServiceBoat.deleteBoat(idBoat: boats[index].id) { (status : Bool, message : String) in
            if status {
                boats.remove(at: index)
                if boats.count > 0 {
                    self.totalRows = boats.count
                }else {
                    self.totalRows = 1
                }
                
                self.tableView.reloadData()
                self.view.hideToastActivity()
                self.alert(title: "Listo", message: "Barco eliminado")
            }else {
                self.view.hideToastActivity()
                self.alert(title: "Error", message: message)
            }
        }
    }
    
}
