//
//  Repository.swift
//  ConcreteTeste
//
//  Created by Tiago Alencar on 15/08/19.
//  Copyright © 2019 TLMA. All rights reserved.
//

import Foundation
import UIKit

class Repository: Decodable {
    var id: Int
    var node_id: String
    var name: String
    var full_name: String
    var forks: Int
    var stargazers_count: Int
    var priv: Bool
    var owner: OwnerRepo
    var description: String
}
