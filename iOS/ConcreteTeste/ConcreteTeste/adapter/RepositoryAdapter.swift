//
//  RepositoryAdapter.swift
//  ConcreteTeste
//
//  Created by Tiago Alencar on 15/08/19.
//  Copyright © 2019 TLMA. All rights reserved.
//

import UIKit

class RepositoryAdapter: UITableViewCell {
    @IBOutlet weak var titleRepository: UILabel!
    @IBOutlet weak var repositoryDescription: UILabel!
    @IBOutlet weak var labelFork: UILabel!
    @IBOutlet weak var labelStar: UILabel!
    @IBOutlet weak var imgUser: UIImageView!
    @IBOutlet weak var username: UILabel!
    @IBOutlet weak var fullname: UILabel!
    
    func setRepository(titleRepository: String, repositoryDescription: String, labelFork: Int,
                       labelStar: Int, imgUser: String, username: String, fullname: String) {
        self.titleRepository.text = titleRepository
        self.repositoryDescription.text = repositoryDescription
        self.labelFork.text = String(labelFork)
        self.labelStar.text = String(labelStar)
        self.username.text = username
        self.fullname.text = fullname
        
        //setimageview
        
        
    }

}
