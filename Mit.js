import fs from "fs/promises";

class Mit {
  constructor() {}

  // Return all files (file size, file name) in `dirname`.
  async list(dirname) {
    try {
      const files = await fs.readdir(dirname);

      return Promise.all(
        files.map(async (file) => {
          const { size } = await fs.stat(`${dirname}/${file}`);
          return { name: file, size: `${size}B` };
        })
      );
    } catch (err) {
      throw new Error(err);
    }
  }

  // Hash each file in `dirname` and return the hash values.
  hash(dirname) {}

  // Compress each file in `dirname` and save with a `.z` extension.
  zlib(dirname) {}
}

export default Mit;
