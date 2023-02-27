import { readdir, readFile, stat } from "node:fs/promises";
import { createHash } from "node:crypto";

class Mit {
  constructor() {}

  // Return all files (file size, file name) in `dirname`.
  async list(dirname) {
    try {
      const files = await readdir(dirname);

      return Promise.all(
        files.map(async (file) => {
          const { size } = await stat(`${dirname}/${file}`);
          return { name: file, size: `${size}B` };
        })
      );
    } catch (err) {
      throw new Error(err);
    }
  }

  // Return hash values of each file's content in `dirname`.
  async hash(dirname) {
    try {
      const files = await readdir(dirname);

      return Promise.all(
        files.map(async (file) => {
          const content = await readFile(`${dirname}/${file}`, {
            encoding: "utf-8",
          });
          const hash = createHash("sha256").update(content).digest("base64");
          return { name: file, hash };
        })
      );
    } catch (err) {
      return err;
    }
  }

  // Compress each file in `dirname` and save with a `.z` extension.
  zlib(dirname) {}
}

export default Mit;
