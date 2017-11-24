//
//  BoatTableViewCell.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 15/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit
protocol BoatCellDelegate {
    func editBoat(cell : BoatTableViewCell)
    func deleteBoat(cell : BoatTableViewCell)
}

class BoatTableViewCell: UITableViewCell {
    //MARK: - IBOutlets
    @IBOutlet weak var labelName: UILabel!
    @IBOutlet weak var labelDescription: UILabel!
    
    //MARK: - Varailables And Constants
    var delegate : BoatCellDelegate?
    
    //MARK: - View Life
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    //MARK: - Actions
    @IBAction func buttonEditPressed(_ sender: Any) {
        if let _ = delegate {
            delegate?.editBoat(cell: self)
        }
    }
    
    @IBAction func deleteButtonPressed(_ sender: Any) {
        if let _ = delegate {
            delegate?.deleteBoat(cell: self)
        }
    }
    
    
    
}
