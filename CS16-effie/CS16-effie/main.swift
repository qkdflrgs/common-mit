//
//  main.swift
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

enum IOError: Error {
  case InvalidInput
}

enum MitError: Error {
  case NotMit(_ command: String)
  case InvalidCommand
  case CannotFetchDirectoryInfo
  
  var actionMessage: String {
    switch self {
    case .NotMit(let command): return "command not found: \(command)"
    case .InvalidCommand: return "존재하지 않는 mit 명령어입니다. 다시 입력해주세요."
    case .CannotFetchDirectoryInfo: return "디렉토리를 탐색할 수 없습니다."
    }
  }
}

protocol MitOutput: CustomStringConvertible {
  associatedtype InfoType
  
  var fileName: String { get }
  var info: InfoType { get }
}

struct ListOutput: MitOutput {
  typealias InfoType = Int
  
  var fileName: String
  var info: Int
  
  var description: String {
    "\(fileName) \(info)KB"
  }
}

struct HashOutput: MitOutput {
  typealias InfoType = String
  
  var fileName: String
  var info: String
  var description: String {
    "\(fileName) = \(info)"
  }
}

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

extension URL {
  var fileSize: Int? {
    get throws {
      guard self.isFileURL else { return nil }
      let infos = try self.resourceValues(forKeys: [.fileSizeKey])
      guard let sizeInByte = infos.fileSize else { return nil }
      return sizeInByte
    }
  }
}

final class Mit {
  private static var fileManager: FileManager {
    return FileManager.default
  }
  
  private static func getEntitiesURL(from path: String) throws -> [URL] {
    let directory = NSURL.fileURL(withPath: path)
    return try fileManager.contentsOfDirectory(at: directory, includingPropertiesForKeys: nil)
  }
  
  private static func list(_ path: String) -> [ListOutput] {
    var outputs: [ListOutput] = []
    do {
      let entities = try getEntitiesURL(from: path)
      outputs = try entities.compactMap { entity in
        let name = entity.lastPathComponent
        guard let size = try entity.fileSize else { return nil }
        return ListOutput(fileName: name, info: size)
      }
    } catch {
      print(error)
    }
    return outputs
  }
  
  private static func process(_ command: MitCommand) -> [any MitOutput] {
    switch command {
    case .list(let path): return list(path)
    default: return []
    }
  }
  
  static func run() {
    while true {
      do {
        let input = try MitIOManager.getInput()
        guard input != "q" else {
          print("terminated")
          return
        }
        
        let command = try MitCommand.getCommand(from: input)
        let outputs = process(command)
        MitIOManager.printOutput(outputs)
        
      } catch {
        if let mitError = error as? MitError {
          print(mitError.actionMessage)
        } else {
          print(error)
        }
      }
      
      print("")
    }
  }
}

Mit.run()
