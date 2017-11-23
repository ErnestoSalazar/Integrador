//
//  UserCardTableViewCell.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 13/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

protocol UserCardCellDelegate {
    func editUser(cell : UserCardTableViewCell)
    func deleteUser(cell : UserCardTableViewCell)
}

class UserCardTableViewCell: UITableViewCell {
    //MARK: - IBOutlets
    @IBOutlet weak var viewColor: UIView!
    @IBOutlet weak var labeTitle: UILabel!
    @IBOutlet weak var labelName: UILabel!
    @IBOutlet weak var labelRfc: UILabel!
    @IBOutlet weak var labelEmail: UILabel!
    @IBOutlet weak var labelRole: UILabel!
    @IBOutlet weak var buttonEdit: UIButton!
    @IBOutlet weak var buttonDelete: UIButton!
    @IBOutlet weak var viewBackground: UIView!
    
    //MARK: - Varailabels And Constants
    var delegate : UserCardCellDelegate?
    
    //MARK: - View Life
    override func awakeFromNib() {
        super.awakeFromNib()
        buttonEdit.isHidden = true
        buttonDelete.isHidden = true
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    //MARK: - Actions
    @IBAction func editButtonPressed(_ sender: Any) {
        if let _ = delegate {
            delegate?.editUser(cell: self)
        }
    }
    
    @IBAction func deleteButtonPressed(_ sender: Any) {
        if let _ = delegate {
            delegate?.deleteUser(cell: self)
        }
    }
    
}
