//
//  ReporteTableViewCell.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 13/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

class ReporteTableViewCell: UITableViewCell {
    //MARK: - IBOutlets
    @IBOutlet weak var labelTitle: UILabel!
    @IBOutlet weak var labelFolio: UILabel!
    @IBOutlet weak var labelAnchoveta: UILabel!
    @IBOutlet weak var labelBacona: UILabel!
    @IBOutlet weak var labelCrinuda: UILabel!
    @IBOutlet weak var labelJaponesa: UILabel!
    @IBOutlet weak var labelMacarela: UILabel!
    @IBOutlet weak var labelMonterrey: UILabel!
    @IBOutlet weak var labelTotal: UILabel!
    
    //MARK: - View Life
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
}
