//
//  URL+fileSize.swift
//  CS16-effie
//
//  Created by Effie on 2023/02/27.
//

import Foundation

extension URL {
  var fileSize: Int? {
    get throws {
      guard self.isFileURL else { return nil }
      let infos = try self.resourceValues(forKeys: [.fileSizeKey])
      guard let sizeInByte = infos.fileSize else { return nil }
      return sizeInByte
    }
  }
  
  var data: Data? {
    get throws {
      try Data(contentsOf: self)
    }
  }
}
