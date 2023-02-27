//
//  Mit.swift
//  CS16-effie
//
//  Created by Effie on 2023/02/27.
//

import Foundation

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
  
  private static func hash(_ path: String) -> [HashOutput] {
    var outputs: [HashOutput] = []
    do {
      let entities = try getEntitiesURL(from: path)
      outputs = try entities.compactMap { entity -> HashOutput? in
        let name = entity.lastPathComponent
        guard let data = try entity.data else { return nil }
        let hash = data.hash
        return HashOutput(fileName: name, info: hash)
      }
    } catch {
      print(error)
    }
    return outputs
  }
  
  private static func process(_ command: MitCommand) -> [any MitOutput] {
    switch command {
    case .list(let path): return list(path)
    case .hash(let path): return hash(path)
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
