//
//  DeliveriesTableViewCell.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 15/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

class DeliveriesTableViewCell: UITableViewCell {
    //MARK: - IBOutlets
    @IBOutlet weak var imageIcon: NSLayoutConstraint!
    @IBOutlet weak var labelTitle: UILabel!
    
    
    //MARK: - View Life
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
}
