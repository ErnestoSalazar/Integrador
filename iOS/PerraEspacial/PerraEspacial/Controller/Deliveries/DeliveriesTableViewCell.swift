//
//  DeliveriesTableViewCell.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 15/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

protocol DeliveriesCellDelegate {
    func editDelivery(cell : DeliveriesTableViewCell)
    func deleteDelivery(cell : DeliveriesTableViewCell)
}

class DeliveriesTableViewCell: UITableViewCell {
    //MARK: - IBOutlets
    @IBOutlet weak var labelFolio: UILabel!
    @IBOutlet weak var labelDate: UILabel!
    @IBOutlet weak var labelTime: UILabel!
    @IBOutlet weak var labelWorkShift: UILabel!
    @IBOutlet weak var labelGeneratedBy: UILabel!
    
    //MARK: - Varailabels And Constants
    var delegate : DeliveriesCellDelegate?
    
    //MARK: - View Life
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    //MARK: - Actions
    @IBAction func editButtonPressed(_ sender: Any) {
        if let _ = delegate {
            delegate?.editDelivery(cell: self)
        }
    }
    
    @IBAction func deleteButtonPressed(_ sender: Any) {
        if let _ = delegate {
            delegate?.deleteDelivery(cell: self)
        }
    }
    
}
