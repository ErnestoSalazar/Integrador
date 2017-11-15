//
//  UserCardTableViewCell.swift
//  PerraEspacial
//
//  Created by saul ulises urias guzmàn on 13/11/17.
//  Copyright © 2017 saul ulises urias guzmàn. All rights reserved.
//

import UIKit

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
    
    //MARK: - View Life
    override func awakeFromNib() {
        super.awakeFromNib()
        buttonEdit.isHidden = true
        buttonDelete.isHidden = true
//        buttonEdit.layer.borderWidth = 1.5
//        buttonEdit.layer.borderColor = #colorLiteral(red: 0.9254901961, green: 0.1333333333, blue: 0.1450980392, alpha: 1)
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
}
