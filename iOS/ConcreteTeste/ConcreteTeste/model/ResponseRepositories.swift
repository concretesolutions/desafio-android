//
//  ResponseRepositories.swift
//  ConcreteTeste
//
//  Created by Tiago Alencar on 15/08/19.
//  Copyright © 2019 TLMA. All rights reserved.
//

import Foundation
import UIKit

class ResponseRepositories: Decodable {
    var total_count: Int?
    var incomplete_results: Bool?
    var items = [Repository]()
}
