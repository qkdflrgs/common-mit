//
//  MitCommand.swift
//  CS16-effie
//
//  Created by Effie on 2023/02/27.
//

import Foundation

enum MitCommand: CustomStringConvertible {
  case list(path: String)
  case hash(path: String)
  case zlib(path: String)
  
  var description: String {
    switch self {
    case .list(let p): return "list - \(p)"
    case .hash(let p): return "hash - \(p)"
    case .zlib(let p): return "zlib - \(p)"
    }
  }
  
  static func getCommand(from input: String) throws -> MitCommand {
    let elems = input.components(separatedBy: .whitespaces)
    
    guard let maybeMit = elems.first, maybeMit == "mit" else { throw MitError.NotMit(elems[0]) }
    
    guard elems.count == 3 else { throw MitError.InvalidCommand }
    
    let command = elems[1]
    let path = elems[2]
    
    switch command {
    case "list":
      return .list(path: path)
    case "hash":
      return .hash(path: path)
    case "zlib":
      return .zlib(path: path)
    default:
      throw MitError.InvalidCommand
    }
  }
}
