//
//  UsersViewController.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 13/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

class UsersViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, UserCardCellDelegate, UISearchBarDelegate {
    //MARK: - IBOutlets
    @IBOutlet weak var tableView: UITableView!
    
    //MARK: - Varailabels And Constants
    var totalRows = 1
    let segueToEditUser = "segueToEditUser"
    
    //MARK: - View Life
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setDelegates()
        self.searchBarSetUp()
        if users.count == 0 {
            self.getUsers()
        }else {
            self.totalRows = users.count
        }
    }
    
    override func viewDidAppear(_ animated: Bool) {
        users.removeAll()
        self.getUsers()
    }
    
    
    //MARK: - Search Bar Delegate
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        if searchText.isEmpty{
            users.removeAll()
            self.getUsers()
            self.totalRows = users.count
            self.tableView.reloadData();
        }else {
            filterTableView(text: searchText);
        }
    }
    
    
    func filterTableView(text: String){
        users = users.filter { (mod) -> Bool in
            return mod.name.lowercased().contains(text.lowercased());
        }
        self.totalRows = users.count
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
        if users.count > 0 {
            let cell = Bundle.main.loadNibNamed("UserCardTableViewCell", owner: self, options: nil)?.first as! UserCardTableViewCell
                let user = users[indexPath.row]
                    cell.labelName.text = "\(user.name) \(user.lastName)"
                    cell.labelEmail.text = user.email
                    cell.labelRfc.text = user.rfc
                    cell.labelRole.text = user.role.name
            cell.delegate = self
            return cell
        }else {
            let cell = Bundle.main.loadNibNamed("NotFoundTableViewCell", owner: self, options: nil)?.first as! NotFoundTableViewCell
            return cell
        }
    }
    
    //MARK: - Cell Delegates
    func editUser(cell: UserCardTableViewCell) {
        if let index = self.tableView.indexPath(for: cell)?.row {
            dataGlobal.set(index, forKey: DataGlobal.keyIndexToEditUser)
            dataGlobal.synchronize()
            self.performSegue(withIdentifier: self.segueToEditUser, sender: self)
        }
    }
    
    func deleteUser(cell: UserCardTableViewCell) {
        if let index = self.tableView.indexPath(for: cell)?.row {
            self.deleteUserAlert(index: index)
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
    
    func getUsers(){
        self.view.makeToastActivity(.center)
        WebServiceUser.getUsers { (status : Bool, usersArray : [User]) in
            if status && usersArray.count > 0 {
                users = usersArray
                self.totalRows = usersArray.count
                self.tableView.reloadData()
                self.view.hideToastActivity()
            }else {
                self.totalRows = 1
                self.tableView.reloadData()
                self.view.hideToastActivity()
            }
        }
    }
    
    func deleteUserAlert(index : Int){
        let alertController = UIAlertController(title: "Eliminar usuario", message: "¿Está seguro de que desea eliminar a \(users[index].name)?", preferredStyle: UIAlertControllerStyle.alert)
        
        let deleteAction = UIAlertAction(title: "Eliminar", style: UIAlertActionStyle.destructive) {
            (result : UIAlertAction) -> Void in
            self.deleteUser(index: index)
        }
        
        let cancelAction = UIAlertAction(title: "Cancelar", style: UIAlertActionStyle.cancel) {
            (result : UIAlertAction) -> Void in
            print("Cancelar")
        }
        
        alertController.addAction(deleteAction)
        alertController.addAction(cancelAction)
        self.present(alertController, animated: true, completion: nil)
    }
    
    func deleteUser(index : Int){
        self.view.makeToastActivity(.center)
        WebServiceUser.deleteUser(idUser: users[index].id) { (status : Bool, message : String) in
            if status {
                users.remove(at: index)
                
                if users.count > 0 {
                    self.totalRows = users.count
                }else {
                    self.totalRows = 1
                }
                
                self.tableView.reloadData()
                self.view.hideToastActivity()
                self.alert(title: "Listo", message: "Usuario eliminado")
            }else {
                self.view.hideToastActivity()
                self.alert(title: "Error", message: message)
            }
        }
    }    
}
