//
//  CargaTableViewCell.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 26/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

protocol CargaCellDelegate {
    func editCarga(cell : CargaTableViewCell)
    func deleteCarga(cell : CargaTableViewCell)
}

class CargaTableViewCell: UITableViewCell {
    //MARK: - IBOutlets
    @IBOutlet weak var labelBoat: UILabel!
    @IBOutlet weak var labelEspecie: UILabel!
    @IBOutlet weak var labelSize: UILabel!
    @IBOutlet weak var labelTemperature: UILabel!
    @IBOutlet weak var labelCondition: UILabel!
    
    //MARK: - Varailabels And Constants
    var delegate : CargaCellDelegate?
    
    //MARK: - View Life
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    //MARK: - Actions
    @IBAction func editButtonPressed(_ sender: Any) {
        print("Edit button pressed")
        if let _ = delegate {
            print("Edit button DELEGATE")
            delegate?.editCarga(cell: self)
        }
    }
    
    @IBAction func deleteButtonPressed(_ sender: Any) {
        if let _ = delegate {
            delegate?.deleteCarga(cell: self)
        }
    }
    
}
