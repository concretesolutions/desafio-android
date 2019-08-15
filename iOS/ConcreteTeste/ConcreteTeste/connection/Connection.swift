//
//  Connection.swift
//  amare38
//
//  Created by Tiago Alencar on 05/08/19.
//  Copyright © 2019 CSPAR Investimentos. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper
import SwiftyJSON

class Connection {
    
    var BASE_URL = "https://api.github.com/search/"
    var params = Parameters()
    
    public func getAllUsers(count: Int, completed: @escaping (_ users: ResponseRepositories)->Void) {
        guard let site = URL(string: self.BASE_URL + "/repositories?q=language:Java&sort=stars&page=\(count)") else {return}
        
        Alamofire.request(site, method: .get, encoding: JSONEncoding.default).responseJSON {
            (response) in
            if response.result.isSuccess {
                guard let data = response.data else {return}
                do {
                    let myResponse = try JSONDecoder().decode(ResponseRepositories.self, from: data)
                    completed(myResponse)
                } catch{}
            }
        }
    }
}
