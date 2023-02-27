//
//  MitIOManager.swift
//  CS16-effie
//
//  Created by Effie on 2023/02/27.
//

import Foundation

final class MitIOManager {
  static func getInput() throws -> String {
    print("$", terminator: " ")
    guard let input = readLine() else { throw IOError.InvalidInput }
    return input.trimmingCharacters(in: .whitespacesAndNewlines)
  }
  
  static func printOutput(_ items: [any MitOutput]) {
    items.forEach { item in print(item.description) }
  }
}
