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
    @IBOutlet weak var labelAnchoveta: UILabel!
    @IBOutlet weak var labelAnchovetaPercent: UILabel!
    @IBOutlet weak var labelBacona: UILabel!
    @IBOutlet weak var labelBaconaPercent: UILabel!
    @IBOutlet weak var labelCrinuda: UILabel!
    @IBOutlet weak var labelCrinudaPercent: UILabel!
    @IBOutlet weak var labelJaponesa: UILabel!
    @IBOutlet weak var labelJaponesaPercent: UILabel!
    @IBOutlet weak var labelMacarela: UILabel!
    @IBOutlet weak var labelMacarelaPercent: UILabel!
    @IBOutlet weak var labelMonterrey: UILabel!
    @IBOutlet weak var labelMonterreyPercent: UILabel!
    @IBOutlet weak var labelRayadillo: UILabel!
    @IBOutlet weak var labelRayadilloPercent: UILabel!    
    @IBOutlet weak var labelTotal: UILabel!
    
    
    
    
    //MARK: - View Life
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
}
